package me.oscar0713.EaseManage.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.oscar0713.EaseManage.Utilities.Configuration;
import me.oscar0713.EaseManage.Utilities.DisplayGroup;
import net.md_5.bungee.api.ChatColor;

public class DisplayName implements Listener{
	private List<DisplayGroup> group = new ArrayList<DisplayGroup>();

	public DisplayName() {
		List<Map<?,?>> configName = Configuration.getDisplayNameList();
		for (Map<?,?> name_group : configName) {
			String prefix = (String) name_group.get("prefix");
			String colorCode = (String) name_group.get("colorCode");
			@SuppressWarnings("unchecked")
			List<String> players = (List<String>) name_group.get("playerNames");
			DisplayGroup oneGroup = new DisplayGroup(prefix, colorCode, players);
			group.add(oneGroup);
		}
		
	}
	
	private int getPlayerIndex(String player) {
		for (int i = 0; i <group.size(); i++) {
			if (group.get(i).inGroup(player)) {
				return i;
			}
		}
		return -1;
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		int index = getPlayerIndex(player.getName());
		
		if (index!= 1) {
			event.setJoinMessage(ChatColor.YELLOW.toString() + player.getName() + " joined the game");
			player.setDisplayName(group.get(index).getPlayerDisplayName(player.getName()));
			player.setPlayerListName(group.get(index).getPlayerDisplayName(player.getName()));
		}
		
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		message = ChatColor.WHITE.toString() + message;
		event.setMessage(message);
		event.setFormat("%s %s");
	}
	
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		int index = getPlayerIndex(player.getName());
		
		if (index != 1) {
			event.setQuitMessage(ChatColor.YELLOW + player.getName() + " left the game");
		}
	}
	
}
