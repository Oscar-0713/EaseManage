package me.oscar0713.EaseManage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.md_5.bungee.api.ChatColor;

public class Reload implements Listener{
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		String cmd = event.getMessage().split(" ")[0].replace("/", "").toLowerCase();
		if (cmd.equalsIgnoreCase("reload")) {
			event.setCancelled(true);
			Player player = event.getPlayer();
			if (!player.hasPermission("reload.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to issue this command. If you think this is an error, please contact server administrator");
				return;
			}
			
			Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " initates to reload the server, it may lag for a while!");
			Bukkit.reload();
			Bukkit.broadcastMessage(ChatColor.GREEN + "Server reload completed.");
			return;
		}
	}
}
