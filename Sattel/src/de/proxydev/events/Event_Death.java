package de.proxydev.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.proxydev.utils.API_Utils;

public class Event_Death implements Listener {
	@EventHandler
	public void onDeaht(PlayerDeathEvent e) {
		
		Player p = e.getEntity();
		if(API_Utils.getSaveStock().containsKey(p)) {
			API_Utils.getSaveStock().get(p).remove();
			API_Utils.getSaveStock().remove(p);
			API_Utils.getLog().writeLog("Pferd wurde gelöscht!");
		}
	}
}
