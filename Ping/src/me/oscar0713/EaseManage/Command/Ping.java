package me.oscar0713.EaseManage.Command;

import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oscar0713.EaseManage.Main;
import net.md_5.bungee.api.ChatColor;

@Deprecated
public class Ping implements CommandExecutor{
	
	public static Main plugin;
	public Ping(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (label.equalsIgnoreCase("ping")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You are not allowed to use this command");
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("easemanage.ping.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
			player.sendMessage(ChatColor.GOLD + "Your ping is " + ChatColor.GREEN + "" + player.getPing() + ChatColor.GOLD + " ms");
			return true;
			
		}
		return false;
	}
	

}
