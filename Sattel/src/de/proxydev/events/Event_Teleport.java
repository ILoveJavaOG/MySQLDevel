package de.proxydev.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import de.proxydev.utils.API_Utils;

public class Event_Teleport implements Listener {
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		if(API_Utils.getSaveStock().containsKey(e.getPlayer())) {
			API_Utils.getSaveStock().get(e.getPlayer()).remove();
			API_Utils.getSaveStock().remove(e.getPlayer());
		}
	}
}
