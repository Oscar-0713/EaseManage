package me.oscar0713.EaseManage.GUI;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUI implements Listener{
	protected GUIinfo info;
	private Inventory inv;
	public GUI(GUIinfo info) {
		this.info = new GUIinfo(info);
		inv = Bukkit.createInventory(null, info.getNumSlot(), info.getName());
		for (int i = 0; i <info.getNumSlot();i++) {
			inv.setItem(i, info.getItem(i));
		}
	}
	
	public GUI (GUI other) {
		info = new GUIinfo(other.info);
		inv = Bukkit.createInventory(null, info.getNumSlot(),info.getName());
		for (int i = 0; i <info.getNumSlot();i++) {
			inv.setItem(i, info.getItem(i));
		}
	}
	
	@EventHandler
	public void onPlayerClick(InventoryClickEvent event) {
		if (!event.getInventory().equals(inv)) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		event.setCancelled(true);
	}
	
}
