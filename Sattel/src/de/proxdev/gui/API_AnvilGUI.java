package de.proxdev.gui;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.proxydev.utils.API_Utils;

/**
* Created by chasechocolate.
*/
public class API_AnvilGUI {
    private Player player;
	@SuppressWarnings("unused")
	private AnvilClickEventHandler handler;
    private static Class<?> BlockPosition;
    private static Class<?> PacketPlayOutOpenWindow;
    private static Class<?> ContainerAnvil;
    private static Class<?> ChatMessage;
    private static Class<?> EntityHuman;
    private HashMap<AnvilSlot, ItemStack> items = new HashMap<AnvilSlot, ItemStack>();
    private Inventory inv;
    private Listener listener;

    private void loadClasses() {
        BlockPosition = API_NMSManager.get().getNMSClass("BlockPosition");
        PacketPlayOutOpenWindow = API_NMSManager.get().getNMSClass("PacketPlayOutOpenWindow");
        ContainerAnvil = API_NMSManager.get().getNMSClass("ContainerAnvil");
        EntityHuman = API_NMSManager.get().getNMSClass("EntityHuman");
        ChatMessage = API_NMSManager.get().getNMSClass("ChatMessage");
    }

    public API_AnvilGUI(final Player player, final AnvilClickEventHandler handler) {
        loadClasses();
        this.player = player;
        this.handler = handler;

        this.listener = new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {
                if (event.getWhoClicked() instanceof Player) {

                    if (event.getInventory().equals(inv)) {
                        event.setCancelled(true);

                        ItemStack item = event.getCurrentItem();
                        int slot = event.getRawSlot();
                        String name = "";

                        if (item != null) {
                            if (item.hasItemMeta()) {
                                ItemMeta meta = item.getItemMeta();

                                if (meta.hasDisplayName()) {
                                    name = meta.getDisplayName();
                                }
                            }
                        }

                        AnvilClickEvent clickEvent = new AnvilClickEvent(AnvilSlot.bySlot(slot), name);

                        handler.onAnvilClick(clickEvent);

                        if (clickEvent.getWillClose()) {
                            event.getWhoClicked().closeInventory();
                        }

                        if (clickEvent.getWillDestroy()) {
                            destroy();
                        }
                    }
                }
            }

            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (event.getPlayer() instanceof Player) {
                    Inventory inv = event.getInventory();
                    player.setLevel(player.getLevel() - 1);
                    if (inv.equals(API_AnvilGUI.this.inv)) {
                        inv.clear();
                        destroy();
                    }
                }
            }

            @EventHandler
            public void onPlayerQuit(PlayerQuitEvent event) {
                if (event.getPlayer().equals(getPlayer())) {
                    player.setLevel(player.getLevel() - 1);
                    destroy();
                }
            }
        };

        Bukkit.getPluginManager().registerEvents(listener, API_Utils.getInstance()); //Replace with instance of main class
    }

    public Player getPlayer() {
        return player;
    }

    public void setSlot(AnvilSlot slot, ItemStack item) {
        items.put(slot, item);
    }

    public void open() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        player.setLevel(player.getLevel() + 1);

        try {
            Object p = API_NMSManager.get().getHandle(player);


            Object container = ContainerAnvil.getConstructor(API_NMSManager.get().getNMSClass("PlayerInventory"), API_NMSManager.get().getNMSClass("World"), BlockPosition, EntityHuman).newInstance(API_NMSManager.get().getPlayerField(player, "inventory"), API_NMSManager.get().getPlayerField(player, "world"), BlockPosition.getConstructor(int.class, int.class, int.class).newInstance(0, 0, 0), p);
            API_NMSManager.get().getField(API_NMSManager.get().getNMSClass("Container"), "checkReachable").set(container, false);

            //Set the items to the items from the inventory given
            Object bukkitView = API_NMSManager.get().invokeMethod("getBukkitView", container);
            inv = (Inventory) API_NMSManager.get().invokeMethod("getTopInventory", bukkitView);

            for (AnvilSlot slot : items.keySet()) {
                inv.setItem(slot.getSlot(), items.get(slot));
            }

            //Counter stuff that the game uses to keep track of inventories
            int c = (int) API_NMSManager.get().invokeMethod("nextContainerCounter", p);

            //Send the packet
            Constructor<?> chatMessageConstructor = ChatMessage.getConstructor(String.class, Object[].class);
            Object playerConnection = API_NMSManager.get().getPlayerField(player, "playerConnection");
            Object packet = PacketPlayOutOpenWindow.getConstructor(int.class, String.class, API_NMSManager.get().getNMSClass("IChatBaseComponent"), int.class).newInstance(c, "minecraft:anvil", chatMessageConstructor.newInstance("Repairing", new Object[]{}), 0);

            Method sendPacket = API_NMSManager.get().getMethod("sendPacket", playerConnection.getClass(), PacketPlayOutOpenWindow);
            sendPacket.invoke(playerConnection, packet);

            //Set their active container to the container
            Field activeContainerField = API_NMSManager.get().getField(EntityHuman, "activeContainer");
            if (activeContainerField != null) {
                activeContainerField.set(p, container);

                //Set their active container window id to that counter stuff
                API_NMSManager.get().getField(API_NMSManager.get().getNMSClass("Container"), "windowId").set(activeContainerField.get(p), c);

                //Add the slot listener
                API_NMSManager.get().getMethod("addSlotListener", activeContainerField.get(p).getClass(), p.getClass()).invoke(activeContainerField.get(p), p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        player = null;
        handler = null;
        items = null;

        HandlerList.unregisterAll(listener);

        listener = null;
    }

    public enum AnvilSlot {
        INPUT_LEFT(0),
        INPUT_RIGHT(1),
        OUTPUT(2);

        private int slot;

        private AnvilSlot(int slot) {
            this.slot = slot;
        }

        public static AnvilSlot bySlot(int slot) {
            for (AnvilSlot anvilSlot : values()) {
                if (anvilSlot.getSlot() == slot) {
                    return anvilSlot;
                }
            }

            return null;
        }

        public int getSlot() {
            return slot;
        }
    }

    public interface AnvilClickEventHandler {
        void onAnvilClick(AnvilClickEvent event);
    }

    public class AnvilClickEvent {
        private AnvilSlot slot;

        private String name;

        private boolean close = true;
        private boolean destroy = true;

        public AnvilClickEvent(AnvilSlot slot, String name) {
            this.slot = slot;
            this.name = name;
        }

        public AnvilSlot getSlot() {
            return slot;
        }

        public String getName() {
            return name;
        }

        public boolean getWillClose() {
            return close;
        }

        public void setWillClose(boolean close) {
            this.close = close;
        }

        public boolean getWillDestroy() {
            return destroy;
        }

        public void setWillDestroy(boolean destroy) {
            this.destroy = destroy;
        }
    }
}