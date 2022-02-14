package me.oscar0713.EaseManage.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.oscar0713.EaseManage.File.Backup;
import net.md_5.bungee.api.ChatColor;

public class BackupCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (label.equalsIgnoreCase("backup")) {
			if (sender.hasPermission("backup.use")) {
				Backup up = new Backup();
				boolean done = up.backupWorlds();
				if (!done) {
					sender.sendMessage(ChatColor.RED + "Backup failed!");
					return true;
				}
				sender.sendMessage(ChatColor.GREEN + "Backup completed!");
			}
		}
		return false;
	}

}
