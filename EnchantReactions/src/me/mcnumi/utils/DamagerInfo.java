package me.mcnumi.utils;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class DamagerInfo {
	
	
	public static ItemStack getDamagerHeldItem(Player player) {
		ItemStack sword = player.getInventory().getItemInMainHand();
		 // Returns the ITEM the player is wielding in main hand
		return sword;
	}
	public static ItemStack getDamagerHelmet(Player player) {
		ItemStack helmet = player.getInventory().getHelmet();
		 // Returns the HELMET the player is wearing
		return helmet;
	}
		
	public static ItemStack getDamagerChestplate(Player player) {
		ItemStack chestplate = player.getInventory().getChestplate();
		 // Returns the CHESTPLATE the player is wearing
		return chestplate;		
	}
	
	public static ItemStack getDamagerLeggings(Player player) {
		ItemStack leggings = player.getInventory().getLeggings();
		 // Returns the LEGGINGS the player is wearing
		return leggings;		
	}
	
	public static ItemStack getDamagerBow(Player player) {
	ItemStack bow = player.getInventory().getItemInMainHand();
		// Returns the CHESTPLATE the player is wearing
		return bow;	
   }
	
	public static ItemStack getDamagerBoots(Player player) {
		ItemStack boots = player.getInventory().getBoots();
		 // Returns the BOOTS the player is wearing
		return boots;		
	}
	
    public static boolean isStackMaterial(ItemStack item, Material material) {
    	if (item == null) {return false;}
    	// Checks if the ITEMSTACK is the MATERIAL
        return item.getType() == material;
    }

	
	public static boolean isItemEnchanted(ItemStack item, Enchantment enchantment) {
		if (item == null) {return false;}
		// Checks if the ITEM contains the ENCHANTMENT
		return item.containsEnchantment(enchantment);		
	}
	
	public static boolean isHelmet(ItemStack item){
		if (item == null) {return false;}
		return item.getType() == Material.DIAMOND_HELMET ||
		item.getType() == Material.CHAINMAIL_HELMET ||
		item.getType() == Material.GOLD_HELMET ||
		item.getType() == Material.IRON_HELMET ||
		item.getType() == Material.LEATHER_HELMET; 
		
	}
	
	public static boolean isChestplate(ItemStack item){
		if (item == null) {return false;}
		return item.getType() == Material.DIAMOND_CHESTPLATE ||
		item.getType() == Material.CHAINMAIL_CHESTPLATE ||
		item.getType() == Material.GOLD_CHESTPLATE ||
		item.getType() == Material.IRON_CHESTPLATE ||
		item.getType() == Material.LEATHER_CHESTPLATE;
	}
	
	public static boolean isLeggings(ItemStack item){
		if (item == null) {return false;}
		return item.getType() == Material.DIAMOND_LEGGINGS ||
		item.getType() == Material.CHAINMAIL_LEGGINGS ||
		item.getType() == Material.GOLD_LEGGINGS ||
		item.getType() == Material.IRON_LEGGINGS ||
		item.getType() == Material.LEATHER_LEGGINGS;
	}
	
	public static boolean isBoots(ItemStack item){
		if (item == null) {return false;}
		return item.getType() == Material.DIAMOND_BOOTS ||
		item.getType() == Material.CHAINMAIL_BOOTS ||
		item.getType() == Material.GOLD_BOOTS ||
		item.getType() == Material.IRON_BOOTS ||
		item.getType() == Material.LEATHER_LEGGINGS;
	}
	
	public static boolean isSword(ItemStack item){
		if (item == null) {return false;}
		return item.getType() == Material.DIAMOND_SWORD ||
		item.getType() == Material.WOOD_SWORD ||
		item.getType() == Material.GOLD_SWORD ||
		item.getType() == Material.IRON_SWORD;
	}
}
