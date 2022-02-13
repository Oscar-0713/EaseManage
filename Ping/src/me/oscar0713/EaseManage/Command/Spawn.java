package me.oscar0713.EaseManage.Command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oscar0713.EaseManage.Utilities.Configuration;
import me.oscar0713.EaseManage.Utilities.Cooldown;
import net.md_5.bungee.api.ChatColor;

public class Spawn implements CommandExecutor{
	private Cooldown<String> cooldowns;
	public Spawn(Long CoolDownTimeInMiliSeconds) {
		cooldowns = new Cooldown<String>(CoolDownTimeInMiliSeconds);
	}
	public Spawn() {
		cooldowns = new Cooldown<String>();
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("spawn") ) {
			if (!Configuration.getCommandEnable("spawn")) {
				sender.sendMessage(ChatColor.RED + "This command is currently disabled!");
				return false;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You are not allowed to use this command");
				return true;
			}
			//Assume all players have permission to use this, fix this later for config.yml
			Player player = (Player) sender;
			if (!player.hasPermission("easemanage.spawn.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to issue this command!");
				return true;
			}
			if (!cooldowns.canUseAbility(player.getName())) {
				player.sendMessage(ChatColor.RED + "You still have " + ((int) cooldowns.getCooldownLeft(player.getName() )/1000) + " seconds cooldown to use this command!");
				return true;
			}
			
			Location loc = player.getBedSpawnLocation();
			player.teleport(loc);
			cooldowns.setCooldown(player.getName());
			player.sendMessage(ChatColor.GOLD + "You have been sent to your spawn point!");
			return true;
		}
		return false;
	}
	

}
