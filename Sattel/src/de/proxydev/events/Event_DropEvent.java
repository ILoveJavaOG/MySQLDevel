package de.proxydev.events;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import de.proxydev.utils.API_Utils;


public class Event_DropEvent implements Listener {
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		
		Item drop = e.getItemDrop();
		ItemStack it = drop.getItemStack();
		if(it.getType().equals(Material.SADDLE)) {
			if(API_Utils.saveStock.containsKey(p))
			{
				if(it.getItemMeta().getLore().get(1).equalsIgnoreCase("§a§o§lOriginal Server Pferd!")) {
					e.setCancelled(true);
				}
			}
		}
	}
}
