package me.oscar0713.EaseManage.TabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class EaseManageCompleter implements TabCompleter{
	List<String> arguements = new ArrayList<String>();
	public EaseManageCompleter() {
		arguements.add("status");
		arguements.add("edit");
	}
	@Override
	public List<String> onTabComplete(CommandSender sedner, Command cmd, String label, String[] args) {
		
		List<String> result = new ArrayList<String>();
		if (args.length == 1) {
			for (String a : arguements) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
		return arguements;
	}

}
