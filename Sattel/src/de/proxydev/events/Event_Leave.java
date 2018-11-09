package de.proxydev.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.proxydev.utils.API_Utils;

public class Event_Leave implements Listener {
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		if(API_Utils.getSaveStock().containsKey(e.getPlayer())) {
			API_Utils.getSaveStock().get(e.getPlayer()).remove();
			API_Utils.getSaveStock().remove(e.getPlayer());
			API_Utils.getLog().writeLog("Pferd wurde gelöscht!");
			API_Utils.getLog().writeLog("Ein Spieler hat den Server verlassen! {" + e.getPlayer().getName()+"}");
		}
	}
}
