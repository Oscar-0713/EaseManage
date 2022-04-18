package me.oscar0713.EaseManage.File;

import java.io.File;

import org.bukkit.inventory.ItemStack;

import me.oscar0713.EaseManage.GUI.GUIinfo;

public class GUIFileHandler {
	//Use yml to handle the settings of a GUI inventory
	private String name;
	private File file;
	
	//Require relative path from the root of jar file
	public GUIFileHandler(String path) {
		String relativeRoot = new File("").getAbsolutePath();
		file = new File(path);
	}
	
	//TODO: return GUIinfo object for constructing GUI object directly
	public GUIinfo getInfo(String section) {
		
		return null;
	}
	
	//Return the ItemStack array for constructing GUIinfo
	private ItemStack[] getItems(String section) {
		
		return null;
	}
	
	
	//Designed format of the YAML
	//numSlot : int # REQUIRED
	//name : String # OPTIONAL
	//items:
	//  -
	//    itemMaterial : Material # REQUIRED
	//    itemIndex : int # REQUIRED
	//    itemNum : int # OPTIONAL, set to 1 in default
	//    itemName : String # OPTIONAL, use & as ChatColor translator
	//    itemLore : List<String> #OPTIONAL
	//    itemEnchantment : Enchantment enum #OPTIONAL
	//    itemFlag : ItemFlag enum # OPTIONAL
	
	
}
