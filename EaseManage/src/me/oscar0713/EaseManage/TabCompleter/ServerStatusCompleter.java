package me.oscar0713.EaseManage.TabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class ServerStatusCompleter implements TabCompleter{
	
	List<String> arguments = new ArrayList<String>();
	public ServerStatusCompleter() {
		arguments.add("tps");
		arguments.add("ping");
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> result = new ArrayList<String>();
		if (args.length == 1) {
			for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
		return arguments;
	}

}
