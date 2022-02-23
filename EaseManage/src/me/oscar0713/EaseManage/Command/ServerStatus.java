package me.oscar0713.EaseManage.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oscar0713.EaseManage.Runnable.TickCalculation;
import me.oscar0713.EaseManage.Utilities.StatusCodeInterpreter;
import me.oscar0713.EaseManage.Utilities.Utilities;
import net.md_5.bungee.api.ChatColor;

public class ServerStatus implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("serverstatus")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You are not allowed to use this command!");
				return false;
			}
			Player player = (Player) sender;
			
			if (!player.hasPermission("easemanage.serverstatus.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to issue this command!");
				return true;
			}
			
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Usage: /serverstatus {tps/ping}");
				return true;
			}
			if (args[0].equalsIgnoreCase("ping")) {
				player.sendMessage(ChatColor.GOLD + "Your current ping: " + 
				StatusCodeInterpreter.getColorCode(StatusCodeInterpreter.getPingSitatusCode(player.getPing())) + 
				player.getPing() + ChatColor.GOLD + " ms");
				return true;
			}
			if (args[0].equalsIgnoreCase("tps")) {
				player.sendMessage(ChatColor.GOLD + "Server current TPS: " +
				StatusCodeInterpreter.getColorCode(StatusCodeInterpreter.getTPSStatusCode(TickCalculation.getTPS()))
				+ Utilities.getRoundOneDecimal(TickCalculation.getTPS()));
				return true;
			}
			player.sendMessage(ChatColor.RED + "Usage: /serverstatus {tps/ping}");
			return true;
		}
		return false;
	}

}
