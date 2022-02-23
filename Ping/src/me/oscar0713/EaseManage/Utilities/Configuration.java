package me.oscar0713.EaseManage.Utilities;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

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
	
//	@Deprecated
//	public static boolean getAutoMessageEnable() {
//		return config.getBoolean("features.auto-message.enable");
//	}
	public static List<String> getAutoMessageList() {
		if (!getFeatureEnable("auto-message")) {
			return null;
		}
		return config.getStringList("features.auto-message.msgs");
	}
	
	public static int getAutoMessageInterval() {
		return config.getInt("features.auto-message.interval");
	}
	
	public static boolean getFeatureEnable(@Nonnull String featureName) {
		return config.getBoolean("features."+featureName+".enable");
	}
	
	public static int getFeatureInterval(@Nonnull String featureName) {
		if (config.isInt("features."+featureName +".interval") && getFeatureEnable(featureName)) {
			return config.getInt("features."+featureName +".interval");
		}
		return -1;
	}
	
	public static List<Map<?,?>> getDisplayNameList() {
		return config.getMapList("features.display-name.group");
	}
	}
