package me.oscar0713.ping;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TickCalculation(), 100L, 1L);
		this.getCommand("ping").setExecutor(new Ping(this));
		this.getCommand("head").setExecutor(new Playerhead());
		Reload reload = new Reload();
		this.getServer().getPluginManager().registerEvents(reload, this);
		this.getServer().getPluginManager().registerEvents(new TPS(),this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
