package de.proxydev.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.proxydev.utils.API_Utils;

public class Event_Join implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		API_Utils.getLog().writeLog("Ein Spieler hat den Server betreten! {" + e.getPlayer().getName() + "}");
	}
}
