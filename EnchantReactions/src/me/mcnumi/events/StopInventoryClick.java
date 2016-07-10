package me.mcnumi.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StopInventoryClick implements Listener{
	
	//Fix Swords Reaction Info clicking :)
	
	@EventHandler
	public void onInventory(InventoryClickEvent event) {
		if(event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Reaction Main Menu") ||
		   event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Reaction Info") ||
		   event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Swords Reaction Info") ||
		   event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Armor Reactions Info") ||
		   event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Bow Reactions Info")) {
			
			event.setCancelled(true);
		}
	}
}
