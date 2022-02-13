package me.oscar0713.EaseManage.Utilities;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import me.oscar0713.EaseManage.Main;
import net.md_5.bungee.api.ChatColor;

public class Configuration {
	private static FileConfiguration config;
	public Configuration(Main main) {
		config = main.getConfig();
	}
	public static boolean getCommandEnable(String command) {
		boolean enable = config.getBoolean("command-enable."+command);
		if (!config.isBoolean("command-enable." + command)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[EaseManage] The command enable setting: *" + command + "* is invalid, it will set to false");
		}
		return enable;
	}
	public static boolean getAutoMessageEnable() {
		return config.getBoolean("features.auto-message.enable");
	}
	public static List<String> getAutoMessageList() {
		if (!getAutoMessageEnable()) {
			return null;
		}
		return config.getStringList("features.auto-message.msgs");
	}
	
	public static int getAutoMessageInterval() {
		return config.getInt("features.auto-message.interval");
	}
}
