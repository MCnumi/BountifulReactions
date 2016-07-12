package me.mcnumi.utils;

import java.util.HashMap;

public class Cooldowns {
    PacketUtils packetUtils = new PacketUtils();
	private int cooldownTime;
	private HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	
	/**
	    * Cooldown class constructor.
	    * @param HashMap The HashMap to use.
	    * @param Integer The cooldown time.
	    */
	public Cooldowns(HashMap<String, Long> cooldowns, int cooldownTime) {
		this.cooldownTime = cooldownTime;
		this.cooldowns = cooldowns;
	}
	
	/**
	    * Sets the players cooldown.
	    * @param playerName The player to retrieve.
	    */	
	public void setPlayerCooldown(String playerName) {
		cooldowns.put(playerName, System.currentTimeMillis());		
	}
	
	/**
	    * Retrieves remaining seconds.
	    * @param playerName The player to retrieve.
	    * @return Seconds left.
	    */
	public Long getSecondsleft(String playerName) {
		return ((cooldowns.get(playerName)/1000) + cooldownTime) -
				(System.currentTimeMillis()/1000);		
	}
	/**
	    * Checks whether or not the player has a cooldown.
	    * @param playerName The player to retrieve.
	    * @return If the player has a cooldown.
	    */
	public boolean isPlayerCooldown(String playerName) {
		return cooldowns.containsKey(playerName);
		
	}
}
