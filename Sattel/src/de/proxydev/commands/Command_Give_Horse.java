package de.proxydev.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import de.proxydev.utils.API_Utils;

public class Command_Give_Horse extends AbstarctCommand{

	public Command_Give_Horse(String commandName, Plugin pl) {
		super(commandName, pl);
	}

	@Override
	public boolean command(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			API_Utils.getLog().writeLog("Command genutzt!");
			if(p.hasPermission("Horse.Give")) {
				if(args.length == 0) {
					Inventory inv = Bukkit.createInventory(null, 54);
					PluginDescriptionFile pdf = API_Utils.getInstance().getDescription();
					File f = new File(API_Utils.getSavePath().replace("{Source}", pdf.getName()));
					File[] list = f.listFiles();
					ItemStack i = new ItemStack(Material.SADDLE);
					ItemMeta im = i.getItemMeta();
					java.util.List<String> Lore = new ArrayList<>();
					YamlConfiguration cfg;
					for(File t : list) {
						if(!t.getName().equalsIgnoreCase("Log")) {
							cfg = YamlConfiguration.loadConfiguration(t);
							im.setDisplayName("§b§o§lHorst-Block Pferd");
							Lore.add(cfg.getString("Config.Name"));
							Lore.add("§a§o§lOriginal Server Pferd!");
							im.setLore(Lore);
							i.setItemMeta(im);
							inv.addItem(i);
							Lore.clear();
							API_Utils.getLog().writeLog("File geladen!");
						}
					}
					p.openInventory(inv);
				}else {p.sendMessage("§c§o§lAchtung, der Parameter wurde nicht gefunden!");}
			}else {p.sendMessage("§c§o§lAchtung, du hast leider keine Berechtigung für diesen Command!");}
		}
		return true;
	}
	
}
