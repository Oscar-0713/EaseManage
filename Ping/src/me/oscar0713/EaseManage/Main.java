package me.oscar0713.EaseManage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.oscar0713.EaseManage.Command.BackupCommand;
import me.oscar0713.EaseManage.Command.Playerhead;
import me.oscar0713.EaseManage.Command.Reload;
import me.oscar0713.EaseManage.Command.ServerStatus;
import me.oscar0713.EaseManage.Command.Spawn;
import me.oscar0713.EaseManage.Command.Stat;
import me.oscar0713.EaseManage.Event.DisplayName;
import me.oscar0713.EaseManage.Runnable.AutoBackup;
import me.oscar0713.EaseManage.Runnable.AutoMessage;
import me.oscar0713.EaseManage.Runnable.TickCalculation;
import me.oscar0713.EaseManage.TabCompleter.ServerStatusCompleter;
import me.oscar0713.EaseManage.Utilities.Configuration;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		//Save default config
		this.saveDefaultConfig();
		//Reload the config file
		this.reloadConfig();
		
		//Configuration class setup
		new Configuration(this);
		
		//Stat command class
		Stat stat = new Stat();
		
		//Register Runnable event to the server
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TickCalculation(), 100L, 1L);
		
		if (Configuration.getFeatureEnable("auto-message")) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoMessage(), 60*20, Configuration.getFeatureInterval("auto-message")*20);			
		}
		
		if (Configuration.getFeatureEnable("auto-backup")) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoBackup(), Configuration.getFeatureInterval("auto-backup")*20, Configuration.getFeatureInterval("auto-backup")*20);
		}
		

		//Deprecated command
		//this.getCommand("ping").setExecutor(new Ping(this));
		
		//Command registration
		this.getCommand("head").setExecutor(new Playerhead());
		this.getCommand("spawn").setExecutor(new Spawn((long) 1800 * 1000));
		this.getCommand("serverstatus").setExecutor(new ServerStatus());
		this.getCommand("serverstatus").setTabCompleter(new ServerStatusCompleter());
		this.getCommand("stat").setExecutor(stat);
		this.getCommand("backup").setExecutor(new BackupCommand(this));
		
		//Event registration
		Reload reload = new Reload();
		this.getServer().getPluginManager().registerEvents(reload, this);
		this.getServer().getPluginManager().registerEvents(stat, this);
		
		if (Configuration.getFeatureEnable("display-name")) {
			this.getServer().getPluginManager().registerEvents(new DisplayName(), this);
		}
		//Deprecated method
		//this.getServer().getPluginManager().registerEvents(new TPS(),this);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
