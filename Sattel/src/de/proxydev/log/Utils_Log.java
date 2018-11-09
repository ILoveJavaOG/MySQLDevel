package de.proxydev.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import de.proxydev.utils.API_Utils;

public class Utils_Log {
	File f;
	YamlConfiguration cfg;
	Integer i;
	
	
	public Utils_Log() {checkLog();}
	
	
	public void checkLog() {
		f = getFile();
		if(!f.exists()) {try {f.createNewFile();} catch (IOException e) {e.printStackTrace();}}
		cfg = YamlConfiguration.loadConfiguration(f);
	}
	
	private File getFile() {
		PluginDescriptionFile pdf = API_Utils.getInstance().getDescription();
		String path = API_Utils.savePath.replace("{Source}", pdf.getName());
		String logPath = API_Utils.getLogPath().replace("{SavePath}", path);
		f = new File(logPath);
		i = f.listFiles().length;
		String logFile = API_Utils.getLogName().replace("{Data}", String.valueOf(i)+ API_Utils.getLogFormat());
		
		return new File(logPath, logFile);
	}
	
	public void writeLog(String log) {
		SimpleDateFormat df = new SimpleDateFormat("dd:MM:yyyy ':' HH:mm:ss");
		cfg.set("Log."+df.format(new Date()), log);
		try {cfg.save(f);}catch(Exception e) {}
	}
}
