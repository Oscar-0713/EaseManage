package me.oscar0713.EaseManage.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.oscar0713.EaseManage.Main;
import me.oscar0713.EaseManage.File.Backup;
import me.oscar0713.EaseManage.Utilities.Configuration;

public class BackupCommand implements CommandExecutor{
	static Main plugin;
	public BackupCommand(Main instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (label.equalsIgnoreCase("backup")) {
			if (!Configuration.getCommandEnable("backup")) {
				sender.sendMessage(ChatColor.RED + "This command is currently disabled!");
			}
			
			if (sender.hasPermission("easemanage.backup.use")) {
				Runnable run = new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						Bukkit.broadcastMessage(ChatColor.AQUA + "[EaseManage] Server administrator initiates a backup, it may lag for a while!");
						Backup up = new Backup();
						boolean done = up.backupWorlds();
						if (!done) {
							Bukkit.broadcastMessage(ChatColor.RED + "[EaseManage] Backup failed!");
							return;
						}
						Bukkit.broadcastMessage(ChatColor.GREEN + "[EaseManage] Backup completed!");
						return;
					}
					
				};
				new Thread(run).start();
				
		}
		return true;
	}
		return false;
	}
}
