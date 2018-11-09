package de.proxydev.utils;


import java.util.HashMap;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import de.proxydev.log.Utils_Log;
import de.proxydev.sattel.Sattel;
import lombok.Getter;
import lombok.Setter;

public class API_Utils {
	@Getter @Setter
	public static Sattel instance;
	@Getter @Setter
	public static String Version;
	@Getter @Setter
	public static String Format;
	@Getter @Setter
	public static String savePath;
	@Getter @Setter
	public static String IDFormat;
	@Getter @Setter
	public static String FileName;
	@Getter @Setter
	public static Boolean LogEnable;
	@Getter @Setter
	public static String LogPath;
	@Getter @Setter
	public static String LogFormat;
	@Getter @Setter 
	public static String LogName;
	@Getter @Setter
	public static Utils_Log log;
	@Getter @Setter
	public static HashMap<Player, Horse> saveStock;
}
