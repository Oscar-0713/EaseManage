package me.oscar0713.EaseManage.Utilities;

import org.bukkit.configuration.file.FileConfiguration;

import me.oscar0713.EaseManage.Main;

public class Configuration {
	private static FileConfiguration config;
	public Configuration(Main main) {
		config = main.getConfig();
	}
	public static boolean getCommandEnable(String command) {
		return config.getBoolean("command-enable."+command);
	}
	
}
