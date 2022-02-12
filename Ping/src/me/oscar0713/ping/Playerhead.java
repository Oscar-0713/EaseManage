package me.oscar0713.ping;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class Playerhead implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (label.equalsIgnoreCase("head")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Console cannot use this command");
				return false;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("head.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
				return false;
			}
			
			if (args.length != 1) {
				player.sendMessage(ChatColor.RED + "Error, invalid arguement!");
				player.sendMessage(ChatColor.RED + "Usage: /head <String>player_name");
				return false;
			}
			if (player.getInventory().firstEmpty() == -1) {
				Location loc = player.getLocation();
				World world = player.getWorld();
				loc.setY(loc.getY()+1);
				world.dropItem(loc, getHead(args[0]));
				return true;
			}
			player.getInventory().addItem(getHead(args[0]));
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private ItemStack getHead(String player) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setOwner(player);
		head.setItemMeta(meta);
		return head;
	}
}
