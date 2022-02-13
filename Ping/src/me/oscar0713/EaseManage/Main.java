package me.oscar0713.EaseManage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.oscar0713.EaseManage.Command.Playerhead;
import me.oscar0713.EaseManage.Command.Reload;
import me.oscar0713.EaseManage.Command.ServerStatus;
import me.oscar0713.EaseManage.Command.Spawn;
import me.oscar0713.EaseManage.TabCompleter.ServerStatusCompleter;
import me.oscar0713.EaseManage.Utilities.TickCalculation;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TickCalculation(), 100L, 1L);
		//this.getCommand("ping").setExecutor(new Ping(this));
		this.getCommand("head").setExecutor(new Playerhead());
		this.getCommand("spawn").setExecutor(new Spawn((long) 1800 * 1000));
		this.getCommand("serverstatus").setExecutor(new ServerStatus());
		this.getCommand("serverstatus").setTabCompleter(new ServerStatusCompleter());
		Reload reload = new Reload();
		this.getServer().getPluginManager().registerEvents(reload, this);
		//this.getServer().getPluginManager().registerEvents(new TPS(),this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
