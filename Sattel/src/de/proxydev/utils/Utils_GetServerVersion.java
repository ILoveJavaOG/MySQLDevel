package de.proxydev.utils;

import de.proxydev.sattel.Sattel;

public class Utils_GetServerVersion {
	Sattel plugin;
	public Utils_GetServerVersion(Sattel in) {
		plugin = in;
	}
	
	public String getServerVersion() {
		String a = plugin.getServer().getClass().getPackage().getName();
		String version = a.substring(a.lastIndexOf('.') + 1);
		return version;
	}
}
