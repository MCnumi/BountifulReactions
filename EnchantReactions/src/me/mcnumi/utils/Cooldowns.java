package me.mcnumi.utils;

import java.util.HashMap;

public class Cooldowns {
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
	    * @param playerName The player to fetch.
	    */	
	public void setPlayerCooldown(String playerName) {
		cooldowns.put(playerName, System.currentTimeMillis());		
	}
	
	/**
	    * Retrieves remaining seconds.
	    * @param playerName The player to fetch.
	    * @return Seconds left.
	    */
	public Long getSecondsleft(String playerName) {
		return ((cooldowns.get(playerName)/1000) + cooldownTime) -
				(System.currentTimeMillis()/1000);		
	}
	
	/**
	    * Removes player from HashMap
	    * @param playerName The player to fetch.
	    */
	public void removePlayer(String playerName) {
		cooldowns.remove(playerName);
	}
	
	public boolean isPlayerCooldown(String playerName) {
		return cooldowns.containsKey(playerName);
		
	}
 }
