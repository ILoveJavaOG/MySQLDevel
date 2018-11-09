package de.proxydev.sattel;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.ilovejava.commands.Command_Create_Horse;
import de.ilovejava.commands.Command_Give_Horse;
import de.proxydev.config.Config_SetUp;
import de.proxydev.events.Event_Death;
import de.proxydev.events.Event_DropEvent;
import de.proxydev.events.Event_Interacc;
import de.proxydev.events.Event_Join;
import de.proxydev.events.Event_Leave;
import de.proxydev.events.Event_Lost;
import de.proxydev.events.Event_SelectColor;
import de.proxydev.events.Event_SelectType;
import de.proxydev.events.Event_Teleport;
import de.proxydev.events.Event_ToggleSneak;
import de.proxydev.log.Utils_Log;
import de.proxydev.utils.Utils_GetServerVersion;
import de.proxydev.utils.API_Utils;

public class Sattel extends JavaPlugin{
	@Override
	public void onEnable() {
		//Instance
		API_Utils.setInstance(this);
		
		//Config Laden
		new Config_SetUp();
		
		//Log Datei erstellen und speichern
		API_Utils.setLog(new Utils_Log());
		API_Utils.getLog().writeLog("Plugin wurde gestartet!");
		//Version abfragen
		Utils_GetServerVersion version = new Utils_GetServerVersion(this);
		if(API_Utils.getVersion().equalsIgnoreCase(version.getServerVersion())) {
			getServer().getConsoleSender().sendMessage("§aDie API version stimmt über ein!");
		}else {
			getServer().getConsoleSender().sendMessage("§cAchtung, das Plugin ist nicht für die Version §b " + version.getServerVersion() + " §cprogrammiert!");
			getServer().getConsoleSender().sendMessage("§cBitte benachrichtigen sie einen Developer der mit dieser API vertraut ist!");
			getServer().getPluginManager().disablePlugin(this);
			API_Utils.getLog().writeLog("Plugin wurde auf Grund einer Falschen Server version nicht gestartet!");
		}
		
		//HasMap erstellen
		API_Utils.setSaveStock(new HashMap<>());
		//Listener
		HashSet<Listener> events = new HashSet<>();
		events.add(new Event_Join());
		events.add(new Event_Interacc());
		events.add(new Event_ToggleSneak());
		events.add(new Event_SelectType());
		events.add(new Event_SelectColor());
		events.add(new Event_DropEvent());
		events.add(new Event_Death());
		events.add(new Event_Teleport());
		events.add(new Event_Leave());
		events.add(new Event_Lost());
		for(Listener l : events) {
			Bukkit.getServer().getPluginManager().registerEvents(l, this);
		}
		
		//Commands;
		new Command_Create_Horse("createHorse", this);
		new Command_Give_Horse("giveHorse", this);
	}
	
	@Override
	public void onDisable() {}
}
