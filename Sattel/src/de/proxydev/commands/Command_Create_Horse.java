package de.proxydev.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.proxydev.create.API_Horse;
import de.proxydev.events.Event_SelectType;
import de.proxydev.gui.API_AnvilGUI;
import de.proxydev.gui.API_AnvilGUI.AnvilClickEvent;
import de.proxydev.gui.API_AnvilGUI.AnvilClickEventHandler;
import de.proxydev.gui.API_AnvilGUI.AnvilSlot;
import de.proxydev.inventorys.Utils_Inventorys;
import de.proxydev.utils.API_Utils;

public class Command_Create_Horse extends AbstarctCommand{

	public Command_Create_Horse(String commandName, Plugin pl) {
		super(commandName, pl);
	}
	API_Horse ch;
	@Override
	public boolean command(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("horse.create")) {
				if(args.length == 0) {
					API_Utils.getLog().writeLog("pferd wird erstellt!");
					getName(p);
				}else {p.sendMessage("§c§o§lAchtung, dieser Parameter wurde nicht gefunden!");}
			}else {p.sendMessage("§c§o§lDu hast leider keine Berechtigung für diesen Command!");}
		}
		return true;
	}
	
	private void getName(Player p) {
		API_Utils.getLog().writeLog("name für Pferd wird eingeben!");
		API_AnvilGUI gui = new API_AnvilGUI(p, new AnvilClickEventHandler() {
			
			@Override
			public void onAnvilClick(AnvilClickEvent event) {
				if(event.getSlot().equals(AnvilSlot.OUTPUT)) {
					event.setWillClose(true);
					event.setWillDestroy(true);
					ch = new API_Horse();
					ch.setName(event.getName());
					new BukkitRunnable() {@Override public void run() {getType(p);}}.runTaskLater(API_Utils.getInstance(), 20);
				}else {
					event.setWillClose(false);
					event.setWillDestroy(false);
				}
			}
		});
		ItemStack i = new ItemStack(Material.CHEST);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("§c§o§lBitte den Pferde Namen eingeben!");
		i.setItemMeta(im);
		gui.setSlot(AnvilSlot.INPUT_LEFT, i);
		try {gui.open();}catch(Exception e) {}
	}
	
	private void getType(Player p) {p.openInventory(Utils_Inventorys.getTypeInventory()); Event_SelectType.ch = ch; API_Utils.getLog().writeLog("Pferde Type wurde nicht gefunden!");}
	
}
