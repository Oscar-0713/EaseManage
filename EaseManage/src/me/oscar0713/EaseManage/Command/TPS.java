package me.oscar0713.EaseManage.Command;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.oscar0713.EaseManage.Runnable.TickCalculation;
import net.md_5.bungee.api.ChatColor;

@Deprecated
public class TPS implements Listener{

	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		String cmd = event.getMessage().split(" ")[0].replace("/", "").toLowerCase();
		if (cmd.equalsIgnoreCase("tps")) {
			event.setCancelled(true);
			Player player = event.getPlayer();
			if (!player.hasPermission("easemanage.tps.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return;
			}
			player.sendMessage(ChatColor.GOLD + "Current server TPS: " + ChatColor.GREEN + TickCalculation.getTPS());
			return;
		}
	}
}
