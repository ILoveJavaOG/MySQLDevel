package de.proxydev.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.proxydev.create.API_Horse;
import de.proxydev.inventorys.Utils_Inventorys;

public class Event_SelectType implements Listener {
	
	public static API_Horse ch;
	
	@EventHandler
	public void InvClick(InventoryClickEvent e) {
		if(!e.getInventory().getName().equalsIgnoreCase("§cType wählen!")) return;
		if(e.getCurrentItem() == null) return;
		if(e.getInventory() == null) return;
		if(e.getCurrentItem().getItemMeta() == null) return;
		e.setCancelled(true);
		
		Player p = (Player) e.getWhoClicked();
		
		switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
		case "§cDonkey":
			ch.setType("DONKEY");
			break;
		case "§cPferd":
			ch.setType("HORSE");
			break;
		case "§cMule":
			ch.setType("MULE");
			break;
		case "§cKnochen Pferd":
			ch.setType("SKELETON_HORSE");
			break;
		case "§cGammel Pferd":
			ch.setType("UNDEAD_HORSE");
			break;
		}
		
		p.closeInventory();
		setColor(p);
	}
	
	private void setColor(Player p) {
		p.openInventory(Utils_Inventorys.getColorInventory());
		Event_SelectColor.hc = ch;
	}
}
