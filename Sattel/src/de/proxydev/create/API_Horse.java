package de.proxydev.create;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import de.proxydev.utils.API_Utils;

public class API_Horse {
	String Name;
	String Color;
	String Permission;
	String Type;
	public API_Horse() {
		
	}
	
	public void setName(String a) {
		Name = a;
	}
	
	public void SetColor(String a) {
		Color = a;
	}
	
	public void setPermission(String a) {
		Permission = a;
	}
	
	public void setType(String a) {
		Type = a;
	}
	
	public String getName() {
		return Name;
	}
	
	public void create() {
		File f = new File(getPath(), Name+API_Utils.getFormat());
		if(!f.exists()){
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			cfg.set("Config.Name", Name);
			cfg.set("Config.Type", Type);
			cfg.set("Config.Color", Color);
			cfg.set("Config.Permission", Permission);
			try {cfg.save(f);}catch(Exception e) {}
		}
	}
	
	public String getPath() {
		PluginDescriptionFile pdf = API_Utils.getInstance().getDescription();
		String path = API_Utils.getSavePath().replace("{Source}", pdf.getName());
		return path;
	}
}
