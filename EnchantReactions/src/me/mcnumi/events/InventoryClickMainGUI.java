package me.mcnumi.events;

import me.mcnumi.guis.InfoGUI;
import me.mcnumi.guis.MainGUI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickMainGUI implements Listener{
	InfoGUI infoGui = new InfoGUI();
	MainGUI mainGui = new MainGUI();
	
	@EventHandler
	public void onInventoryClickMain(InventoryClickEvent event) {
		if (event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + "" + "Reaction Main Menu")) {
			
			if(event.getCurrentItem().equals(mainGui.getInfoItem())) {
				
			Player player = (Player) event.getWhoClicked();
			infoGui.openInfoInv(player);
			
			}
		}
	}
}
