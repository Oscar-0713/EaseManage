package me.oscar0713.EaseManage.Utilities;

import org.bukkit.plugin.PluginDescriptionFile;

import me.oscar0713.EaseManage.Main;

public class PluginDesc {
	private PluginDescriptionFile desc;
	public PluginDesc(Main plugin) {
		desc = plugin.getDescription();
	}
	
	public static PluginDescriptionFile getDescription() {
		return desc;
	}
}
