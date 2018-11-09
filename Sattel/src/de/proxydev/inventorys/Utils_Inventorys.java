package de.proxydev.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils_Inventorys {
	public static Inventory getTypeInventory() {
		Inventory inv = Bukkit.createInventory(null, 27, "�cType w�hlen!");
		
		ItemStack i = new  ItemStack(Material.MONSTER_EGG);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("�cDonkey");
		i.setItemMeta(im);
		inv.addItem(i);
		
		im.setDisplayName("�cPferd");
		i.setItemMeta(im);
		inv.addItem(i);
		
		im.setDisplayName("�cMule");
		i.setItemMeta(im);
		inv.addItem(i);
		
		im.setDisplayName("�cKnochen Pferd");
		i.setItemMeta(im);
		inv.addItem(i);
		
		im.setDisplayName("�cGammel Pferd");
		i.setItemMeta(im);
		inv.addItem(i);
		
		return inv;
	}
	
	public static Inventory getColorInventory() {
		Inventory inv = Bukkit.createInventory(null, 27, "�cFarbe w�hlen!");
		
		ItemStack i = new ItemStack(Material.WOOL,1,(short)15);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("�cSchwarz");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL,1,(short)12);
		im = i.getItemMeta();
		im.setDisplayName("�cBraun");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL,1,(short)10);
		im = i.getItemMeta();
		im.setDisplayName("�cNuss");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL,1,(short)14);
		im = i.getItemMeta();
		im.setDisplayName("�cCrem");
		i.setItemMeta(im);
		inv.addItem(i);
		
		
		i = new ItemStack(Material.WOOL, 1, (short)12);
		im = i.getItemMeta();
		im.setDisplayName("�cDunkel Braun");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL,1,(short)7);
		im = i.getItemMeta();
		im.setDisplayName("�cGrau");
		i.setItemMeta(im);
		inv.addItem(i);
		
		i = new ItemStack(Material.WOOL);
		im = i.getItemMeta();
		im.setDisplayName("�cWei�");
		i.setItemMeta(im);
		inv.addItem(i);
		
		return inv;
	}
}
