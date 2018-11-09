package de.proxydev.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import de.proxydev.utils.API_Utils;

public class Event_Lost implements Listener {
	@EventHandler
	public void lost(PlayerKickEvent e) {
		if(API_Utils.getSaveStock().containsKey(e.getPlayer())) {
			API_Utils.getSaveStock().get(e.getPlayer()).remove();
			API_Utils.getSaveStock().remove(e.getPlayer());
			API_Utils.getLog().writeLog("Pferd wurde gelöscht!");
			API_Utils.getLog().writeLog("Spieler wurde gekickt! {" + e.getPlayer().getName() + "}");
		}
	}
}
