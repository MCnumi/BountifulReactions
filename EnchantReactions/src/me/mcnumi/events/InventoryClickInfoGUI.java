package me.mcnumi.events;

import me.mcnumi.guis.InfoGUI;
import me.mcnumi.guis.SwordsGUI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickInfoGUI implements Listener{
	
	InfoGUI infoGui = new InfoGUI();
	SwordsGUI swordsGui = new SwordsGUI();
	
	@EventHandler
	public void onInventoryClickMain(InventoryClickEvent event) {
		if (event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Reaction Info")) {
			
			if(event.getCurrentItem().equals(infoGui.getSwordInfo())) {
				
			Player player = (Player) event.getWhoClicked();
			swordsGui.openSwordsInv(player);
			
			}
		}
           if (event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Reaction Info")) {
			
			   if(event.getCurrentItem().equals(infoGui.getBowInfo())) {
				
			Player player = (Player) event.getWhoClicked();
			swordsGui.openSwordsInv(player);
			
			}
			if (event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Reaction Info")) {
				
				if(event.getCurrentItem().equals(infoGui.getArmorInfo())) {
					
				Player player = (Player) event.getWhoClicked();
				swordsGui.openSwordsInv(player);
				
				}
			}
		}
	}

}
