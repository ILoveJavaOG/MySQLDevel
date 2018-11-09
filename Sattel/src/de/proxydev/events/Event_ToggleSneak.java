package de.proxydev.events;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

import de.proxydev.utils.API_Utils;

public class Event_ToggleSneak implements Listener {
	@EventHandler
	public void onToggle(VehicleExitEvent e) {
		if(e.getVehicle() instanceof Horse && e.getExited() instanceof Player) {
			Player p = (Player) e.getExited();
			if(API_Utils.getSaveStock().containsKey(p)) {
				Horse h = API_Utils.getSaveStock().get(p);
				h.remove();
				API_Utils.getSaveStock().remove(p);
			}
		}
 	}
}
