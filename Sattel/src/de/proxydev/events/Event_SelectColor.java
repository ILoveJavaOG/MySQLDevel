package de.proxydev.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.proxydev.create.API_Horse;

public class Event_SelectColor implements Listener {
	
	public static API_Horse hc;
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(!e.getInventory().getName().equalsIgnoreCase("�cFarbe w�hlen!")) return;
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getItemMeta() == null) return;
		if(e.getInventory() == null) return;
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
			case "�cSchwarz":
				hc.SetColor("BLACK");
				questCreate(e.getInventory(), p);
			break;
			case "�cBraun":
				hc.SetColor("BROWN");
				questCreate(e.getInventory(), p);
				break;
			case "�cNuss":
				hc.SetColor("CHESTNUT");
				questCreate(e.getInventory(), p);
				break;
			case "�cCrem":
				hc.SetColor("CREAMY");
				questCreate(e.getInventory(), p);
				break;
			case "�cDunkel Braun":
				hc.SetColor("DARK_BROWN");
				questCreate(e.getInventory(), p);
				break;
			case "�cGrau":
				hc.SetColor("GRAY");
				questCreate(e.getInventory(), p);
				break;
			case "�cWei�":
				hc.SetColor("WHITE");
				questCreate(e.getInventory(), p);
				break;
			case "�aErstellen?":
				hc.setPermission("Horse."+hc.getName());
				p.closeInventory();
				hc.create();
				p.sendMessage("�c�o�lDas Pferd wird nun erstellt, schau bitte in die Datenbank!");
				break;
				
			case "�cAbbrechen!":
				p.closeInventory();
				break;
		}
	}
	
	private void questCreate( Inventory inv, Player p) {
		inv.clear();
		
		ItemStack i = new ItemStack(Material.WOOL,1,(short)13);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("�aErstellen?");
		i.setItemMeta(im);
		inv.setItem(10, i);
		
		i = new ItemStack(Material.WOOL,1,(short)14);
		im = i.getItemMeta();
		im.setDisplayName("�cAbbrechen!");
		i.setItemMeta(im);
		inv.setItem(16, i);
		
		p.updateInventory();
	}
}
