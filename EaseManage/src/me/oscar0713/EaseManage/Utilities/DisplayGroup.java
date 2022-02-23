package me.oscar0713.EaseManage.Utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class DisplayGroup {
	private String prefix;
	private String colorCode;
	public List<String> playerList = new ArrayList<String>();
	
	public DisplayGroup(String prefix, String colorCode, List<String> list) {
		this.prefix = prefix;
		this.colorCode = colorCode;
		for (String player : list) {
			playerList.add(player);
		}
	}
	
	public boolean inGroup(String player) {
		return playerList.contains(player);
	}
	
	public String getPlayerDisplayName(String player) {
		if (!inGroup(player)) {
			return null;
		}
		return ChatColor.translateAlternateColorCodes('&', prefix +""+ colorCode +""+ player + "&f");
	}
	
	public String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', prefix);
	}
}
