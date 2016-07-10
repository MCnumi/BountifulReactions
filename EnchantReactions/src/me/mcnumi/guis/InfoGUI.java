package me.mcnumi.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InfoGUI {
	private Inventory infoInv;
	
	private ItemStack createItem(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

	private ItemStack armor = createItem(Material.DIAMOND_CHESTPLATE, 1, ChatColor.GOLD + "" + 
			ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Armor Reactions");
	
	private ItemStack sword = createItem(Material.DIAMOND_SWORD, 1, ChatColor.GOLD + "" + 
			ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Sword Reactions");
	
	private ItemStack bow = createItem(Material.BOW, 1, ChatColor.GOLD + "" + 
			ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Bow Reactions");
	
	public void openInfoInv(Player p) {
		infoInv = Bukkit.createInventory(null, 9, ChatColor.RED + "" + ChatColor.BOLD + "" + "Reaction Info");
		infoInv.setItem(2, armor);
		infoInv.setItem(4, sword);
		infoInv.setItem(6, bow);
		p.openInventory(infoInv);
	}
	
	public ItemStack getArmorInfo() {
		return armor;
	}
	
	public ItemStack getSwordInfo() {
		return sword;
	}
	
	public ItemStack getBowInfo() {
		return bow;
	}
}
