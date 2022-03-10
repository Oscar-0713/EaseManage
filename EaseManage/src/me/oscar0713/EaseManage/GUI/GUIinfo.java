package me.oscar0713.EaseManage.GUI;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

//A class which provides essential info to construct an GUI
public class GUIinfo {
	private String name;
	private ItemStack[] items;
	private int numSlot;
	
	//Must provide a length of ItemStack array equal to numSlot
	public GUIinfo(String name, int numSlot, ItemStack [] items) {
		this.name = name;
		this.numSlot = numSlot;
		this.items = new ItemStack [numSlot];
		for (int i = 0; i < numSlot; i++) {
			if (items[i] == null) {
				this.items[i] = new ItemStack(Material.AIR);
			}
			else {
				this.items[i] = new ItemStack(items[i]);
			}
		}
	}
	
	public GUIinfo(GUIinfo other) {
		this.name = other.name;
		this.numSlot = other.numSlot;
		this.items = new ItemStack[this.numSlot];
		for (int i = 0; i<this.numSlot;i++) {
			items[i] = new ItemStack(other.items[i]);
		}
	}
	
	public boolean hasItem(int index) {
		if (index >= numSlot) {
			return false;
		}
		return (items[index].getType() != Material.AIR);
	}
	
	public ItemStack getItem(int index) {
		if (index >= numSlot) {
			return null;
		}
		return items[index];
	}
	
	public String getName() {
		return name;
	}
	
	public void setItem(ItemStack item, int index) {
		items[index] = new ItemStack(item);
	}
	
	public int getNumSlot() {
		return numSlot;
	}
}
