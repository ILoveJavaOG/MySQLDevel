package de.proxydev.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils_Inventorys {
	public static Inventory getTypeInventory() {
		Inventory inv = Bukkit.createInventory(null, 27, "ßcType w‰hlen!");
		
		ItemStack i = new  ItemStack(Material.MONSTER_EGG);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("ßcDonkey");
		i.setItemMeta(im);
		inv.addItem(i);
		
		im.setDisplayName("ßcPferd");
		i.setItemMeta(im);
		inv.addItem(i);
		
		im.setDisplayName("ßcMule");
		i.setItemMeta(im);
		inv.addItem(i);
		
		im.setDisplayName("ßcKnochen Pferd");
		i.setItemMeta(im);
		inv.addItem(i);
		
		im.setDisplayName("ßcGammel Pferd");
		i.setItemMeta(im);
		inv.addItem(i);
		
		return inv;
	}
	
	public static Inventory getColorInventory() {
		Inventory inv = Bukkit.createInventory(null, 27, "ßcFarbe w‰hlen!");
		
		ItemStack i = new ItemStack(Material.WOOL,1,(short)15);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("ßcSchwarz");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL,1,(short)12);
		im = i.getItemMeta();
		im.setDisplayName("ßcBraun");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL,1,(short)10);
		im = i.getItemMeta();
		im.setDisplayName("ßcNuss");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL,1,(short)14);
		im = i.getItemMeta();
		im.setDisplayName("ßcCrem");
		i.setItemMeta(im);
		inv.addItem(i);
		
		
		i = new ItemStack(Material.WOOL, 1, (short)12);
		im = i.getItemMeta();
		im.setDisplayName("ßcDunkel Braun");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL,1,(short)7);
		im = i.getItemMeta();
		im.setDisplayName("ßcGrau");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL);
		im = i.getItemMeta();
		im.setDisplayName("ßcWeiﬂ");
		i.setItemMeta(im);
		inv.addItem(i);
		
		return inv;
	}
}
