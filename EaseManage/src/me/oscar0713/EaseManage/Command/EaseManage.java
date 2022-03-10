package me.oscar0713.EaseManage.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oscar0713.EaseManage.Utilities.PluginDesc;
import net.md_5.bungee.api.ChatColor;

public class EaseManage implements CommandExecutor{

	//This is the basic command, cannot be disabled
	//But the feature of editing config.yml can be disable
	//TBC
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("easemanage")) {
			if (!sender.hasPermission("easemanage.modify.use")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
			
			//Check about the args number
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GREEN + "EaseManage" + ChatColor.AQUA + PluginDesc.getDescription().getVersion());
				sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "For more information, please refer to " + PluginDesc.getDescription().getWebsite());
				return true;
			}
			
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("status")) {
					if (!(sender instanceof Player)) {
						//Console response, cli
						return true;
					} else {
						//Console response, gui
						return true;
					}
				}
				
			}
		}
		
		return false;
	}

}
