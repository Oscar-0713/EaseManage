package me.oscar0713.ping;

import java.util.HashMap;
import java.util.Map;

public class Cooldown<HashedTag> {
	private Map<HashedTag, Long> map = new HashMap<HashedTag,Long>();
	//Time set as MiliSeconds
	private Long cooldownTime;
	
	public Cooldown(Long Miliseconds) {
		cooldownTime = Miliseconds;
	}
	public Cooldown() {
		cooldownTime = (long) 0.0;
	}
	
	public boolean canUseAbility(HashedTag HashedTagValue) {
		if (!map.containsKey(HashedTagValue)) {
			return true;
		}
		if (map.get(HashedTagValue) <= System.currentTimeMillis()) {
			return true;
		}
		return false;
	}
	
	public void setCooldown(HashedTag HashedTagValue) {
		if (map.containsKey(HashedTagValue)) {
			map.remove(HashedTagValue);
		}
		map.put(HashedTagValue, cooldownTime + System.currentTimeMillis());
	}
	
	public long getCooldownLeft(HashedTag HashedTagValue) {
		if (canUseAbility(HashedTagValue)) {
			return 0;
		}
		return (map.get(HashedTagValue) - System.currentTimeMillis());
	}
	
	public long getCooldownSet() {
		return cooldownTime;
	}
}
