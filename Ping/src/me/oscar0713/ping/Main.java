package me.oscar0713.ping;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		this.getCommand("ping").setExecutor(new Ping());
		Reload reload = new Reload();
		this.getServer().getPluginManager().registerEvents(reload, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
