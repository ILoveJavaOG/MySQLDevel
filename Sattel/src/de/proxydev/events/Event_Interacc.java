package de.proxydev.events;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;

import de.proxydev.utils.API_Utils;

public class Event_Interacc implements Listener {
	@EventHandler
	public void onInter(PlayerInteractEvent e) {
		if(e.getPlayer().getItemInHand() == null) return;
		if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) return;
		
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getPlayer().getItemInHand().getType().equals(Material.SADDLE)) {
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§b§o§lHorst-Block Pferd")) {
					if(e.getPlayer().getItemInHand().getItemMeta().getLore().get(1).equalsIgnoreCase("§a§o§lOriginal Server Pferd!")) {
						String Name = e.getPlayer().getItemInHand().getItemMeta().getLore().get(0);
						PluginDescriptionFile pdf = API_Utils.getInstance().getDescription();
						File f = new File(API_Utils.getSavePath().replace("{Source}", pdf.getName()),Name+API_Utils.getFormat());
						if(f.exists()) {
							YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
							Horse h = (Horse) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.HORSE);
							h.setTamed(true);
							h.setVariant(Variant.valueOf(cfg.getString("Config.Type")));
							h.setColor(Color.valueOf(cfg.getString("Config.Color")));
							h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
							h.setPassenger(e.getPlayer());
							h.setAgeLock(true);
							h.setAge(30);
							API_Utils.getSaveStock().put(e.getPlayer(), h);
							API_Utils.getLog().writeLog("Pferd wurde erstellt!");
						}
					}
				}
			}
		}
	}
}
