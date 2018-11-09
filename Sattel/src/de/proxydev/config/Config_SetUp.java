package de.proxydev.config;

import org.bukkit.configuration.file.YamlConfiguration;

import de.proxydev.utils.API_Utils;

public class Config_SetUp {
	public Config_SetUp() {
		KeySet("Config.SavePath", "plugins/{Source}/Database");
		KeySet("Config.FileFormat", ".yml");
		KeySet("Config.FileName", "{FileName}");
		KeySet("Config.EntetyEnum", "ID");
		KeySet("Config.Permission.Pool", 35);
		KeySet("Config.Permission.Query", 35);
		KeySet("Config.Minecraft.Version.API","v1_8_R3");
		KeySet("Config.Log.enable", true);
		KeySet("Config.Log.SaveFoulder","{SavePath}/Log");
		KeySet("Config.Log.Format", ".cfg");
		KeySet("Config.Log.FileName", "Log-{Data}");
		loadData();
	}
	
	private void loadData() {
		YamlConfiguration cfg = (YamlConfiguration) API_Utils.getInstance().getConfig();
		API_Utils.setVersion(cfg.getString("Config.Minecraft.Version.API"));
		API_Utils.setIDFormat(cfg.getString("Config.EntetyEnum"));
		API_Utils.setFormat(cfg.getString("Config.FileFormat"));
		API_Utils.setSavePath(cfg.getString("Config.SavePath"));
		API_Utils.setFileName(cfg.getString("Config.FileName"));
		API_Utils.setLogEnable(cfg.getBoolean("Config.Log.enable"));
		API_Utils.setLogFormat(cfg.getString("Config.Log.Format"));
		API_Utils.setLogName(cfg.getString("Config.Log.FileName"));
		API_Utils.setLogPath(cfg.getString("Config.Log.SaveFoulder"));
	}
	
	private void KeySet(String key, Object obj) {
		if(!API_Utils.getInstance().getConfig().isSet(key)) {
			API_Utils.getInstance().getConfig().set(key, obj);
			API_Utils.getInstance().saveConfig();
		}
	}
}
