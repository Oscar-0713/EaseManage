package me.oscar0713.EaseManage.Runnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.oscar0713.EaseManage.File.BackupFileHandler;
import me.oscar0713.EaseManage.Utilities.Configuration;

public class AutoBackup implements Runnable{
	volatile private boolean shutdown = false;
	
	@Override
	public void run() {
		if (!shutdown) {
			Runnable run = new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					Bukkit.broadcastMessage(ChatColor.AQUA + "[EaseManage] Server auto-backup starts, it may lag for a while!");
					Bukkit.broadcastMessage(ChatColor.RED + "[EaseManage] This function is still in BETA.");
					BackupFileHandler up = new BackupFileHandler();
					//BackupFileHandler.saveWorld();
					boolean done = up.backupWorlds();
					if (!done) {
						Bukkit.broadcastMessage(ChatColor.RED + "[EaseManage] Backup failed, please check error for more details.");
						return;
					}
					
					if (Configuration.getMaxBackups() != -1 && up.getNumberOfFiles() > Configuration.getMaxBackups()) {
						up.deleteOldestBackup();
					}
					
					Bukkit.broadcastMessage(ChatColor.GREEN + "[EaseManage] Backup completed!");
					return;
				}
				
			};
			new Thread(run).start();
		}

	}
	
	public void deactivate() {
		shutdown = true;
	}

}
