package me.oscar0713.EaseManage.Utilities;

import net.md_5.bungee.api.ChatColor;

public class StatusCodeInterpreter {
	public static final ChatColor getColorCode(StatusCode code) {
		switch(code) {
		case GREEN:
			return ChatColor.GREEN;
		case RED:
			return ChatColor.RED;
		case YELLOW:
			return ChatColor.YELLOW;
		case ORANGE:
			return ChatColor.GOLD;
		}
		return ChatColor.WHITE;
	}
	
	public static final StatusCode getPingSitatusCode(int ping) {
		if (ping <= 30) {
			return StatusCode.GREEN;
		}
		if (ping <= 200) {
			return StatusCode.YELLOW;
		}
		if (ping <= 1000) {
			return StatusCode.ORANGE;
		}
		return StatusCode.RED;
	}
	
	public static final StatusCode getTPSStatusCode(double tps) {
		if (tps >= 15.0) {
			return StatusCode.GREEN;
		}
		if (tps >= 10.0) {
			return StatusCode.YELLOW;
		}
		if (tps >= 5.0) {
			return StatusCode.ORANGE;
		}
		return StatusCode.RED;
	}
}
