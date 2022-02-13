package me.oscar0713.EaseManage.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.oscar0713.EaseManage.Utilities.Configuration;
import me.oscar0713.EaseManage.Utilities.Utilities;
import net.md_5.bungee.api.ChatColor;

public class Stat implements CommandExecutor, Listener {
	private Map<String, Inventory> invs = new HashMap<String, Inventory>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (label.equalsIgnoreCase("stat")) {
			if (!Configuration.getCommandEnable("stat")) {
				sender.sendMessage(ChatColor.RED + "This command is currently disabled!");
				return false;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You are not allowed to use this command!");
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("easemanage.stat.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to issue this comnand!");
				return true;
			}
			updateInv(player);
			player.openInventory(invs.get(player.getName()));
			return true;
		}
		return false;
	}
	
	private Inventory createInventory() {
		//Initailize global GUI inventory
		Inventory inv = Bukkit.createInventory(null, 45, "Player Statistics");
		
		//Set Playerheads for showing GUI
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		inv.setItem(13, item);
		
		//Set DeathCount
		item = new ItemStack(Material.SKELETON_SKULL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Death Count");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "The number of death since you joined the survival.");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(28, item);
		
		//Set mobkills
		item.setType(Material.DIAMOND_SWORD);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Mobkill count");
		lore.clear();
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "The number of mobkill counts since you began your journey.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(29, item);
		
		//Set walking distance
		item.setType(Material.LEATHER_BOOTS);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Walking distance");
		lore.clear();
		lore.add("");
		lore.add(ChatColor.GRAY + ""+ ChatColor.ITALIC + "The distance that you have walked.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(30, item);
		
		//Set time in the server
		item.setType(Material.CLOCK);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Online Time");
		lore.clear();
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "The time that you played.");
		item.setItemMeta(meta);
		inv.setItem(31, item);
		
		//Set closed
		ItemStack item_close = new ItemStack(Material.BARRIER);
		ItemMeta meta_close = item_close.getItemMeta();
		meta_close.setDisplayName(ChatColor.RED + "Close");
		item_close.setItemMeta(meta_close);
		inv.setItem(40, item_close);
		
		return inv;
	}
	
	private void updateInv(final Player player) {
		//Remove the inv if it is in the hash map
		if (invs.containsKey(player.getName())) {
			invs.remove(player.getName());
		}
		Inventory inv_player = createInventory();
		
		//Update player head
		ItemStack head = inv_player.getItem(13);
		SkullMeta head_meta = (SkullMeta) head.getItemMeta();
		head_meta.setOwningPlayer(player);
		head_meta.setDisplayName(player.getName());
		head.setItemMeta(head_meta);
		inv_player.setItem(13, head);
		
		//Update death count
		ItemStack item = inv_player.getItem(28);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(addPlayerStat(player, meta.getLore(), Statistic.DEATHS));
		item.setItemMeta(meta);
		inv_player.setItem(28, item);
		
		//Update ModKills
		item = inv_player.getItem(29);
		meta = item.getItemMeta();
		meta.setLore(addPlayerStat(player, meta.getLore(), Statistic.MOB_KILLS));
		item.setItemMeta(meta);
		inv_player.setItem(29, item);
		
		//Update walking distance
		item = inv_player.getItem(30);
		meta = item.getItemMeta();
		meta.setLore(addPlayerStat(player, meta.getLore(), Statistic.WALK_ONE_CM));
		item.setItemMeta(meta);
		inv_player.setItem(30, item);
		
		//Update playing time
		item = inv_player.getItem(31);
		meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		double ticks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
		ticks = ticks /20 / 60 / 60;
		lore.add(ChatColor.AQUA + "Current count: " + Utilities.getRoundOneDecimal(ticks) + " hours");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv_player.setItem(31, item);
		invs.put(player.getName(), inv_player);
	}
	
	private List<String> addPlayerStat(Player player, List<String> lore, Statistic stat) {
		lore.add(ChatColor.AQUA + "Current count: " + player.getStatistic(stat));
		return lore;
	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!invs.containsKey(event.getWhoClicked().getName())) {
			return;
		}
		if (!event.getInventory().equals(invs.get(event.getWhoClicked().getName()))) {
			return;
		}
		if (event.getCurrentItem() == null ) {
			return;
		}
		event.setCancelled(true);
		
		if (event.getCurrentItem().getType() == Material.BARRIER) {
			Player player = (Player) event.getWhoClicked();
			player.closeInventory();
		}
	}
}
