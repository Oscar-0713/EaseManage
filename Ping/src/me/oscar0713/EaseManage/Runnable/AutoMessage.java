package me.oscar0713.EaseManage.Runnable;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.oscar0713.EaseManage.Utilities.Configuration;

public class AutoMessage implements Runnable{
	private List<String> msgs;
	private Random r = new Random();
	
	public AutoMessage() {
		msgs = Configuration.getAutoMessageList();
	}
	
	@Override
	public void run() {
//		try {
//			Thread.sleep(sleepTime);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		int index = 0;
		if (msgs.size() >1) {
			index = r.nextInt() % (msgs.size() -1);
		}
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',msgs.get(index)));
	}

}
