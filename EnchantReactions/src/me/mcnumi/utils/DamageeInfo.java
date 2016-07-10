package me.mcnumi.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DamageeInfo {
	
	public ItemStack getDamageeHeldItem(Player player) {
		ItemStack sword = player.getInventory().getItemInMainHand();
		 // Returns the ITEM the player is wielding in main hand
		return sword;
	}
	
	public ItemStack getDamageeHelmet(Player player) {
		ItemStack helmet = player.getInventory().getHelmet();
		 // Returns the HELMET the player is wearing
		return helmet;
	}
		
	public ItemStack getDamageeChestplate(Player player) {
		ItemStack chestplate = player.getInventory().getChestplate();
		 // Returns the CHESTPLATE the player is wearing
		return chestplate;		
	}
	
	public ItemStack getDamageeLeggings(Player player) {
		ItemStack leggings = player.getInventory().getLeggings();
		 // Returns the LEGGINGS the player is wearing
		return leggings;		
	}
	
	public ItemStack getDamageeBoots(Player player) {
		ItemStack boots = player.getInventory().getBoots();
		 // Returns the BOOTS the player is wearing
		return boots;		
	}
	
	public ItemStack[] getDamageeArmor(Player player) {
		ItemStack[] allArmorSlots = player.getInventory().getArmorContents();
		// Returns the BOW the player is wielding in main hand
	    return allArmorSlots;		
    }
	
	public ItemStack getDamageeBow(Player player) {
	ItemStack bow = player.getInventory().getItemInMainHand();
	 // Returns the BOW the player is wielding in main hand
	return bow;		
    }
    public boolean isStackMaterial(ItemStack item, Material material) {
    	// Checks if the ITEMSTACK is the MATERIAL
        return item.getType() == material;
    }
	
	public boolean isItemEnchanted(ItemStack item, Enchantment enchantment) {
		// Checks if the ITEM contains the ENCHANTMENT
		return item.containsEnchantment(enchantment);		
	}
	
	
	public boolean isHelmet(ItemStack item){
		return item.getType() == Material.DIAMOND_HELMET ||
		item.getType() == Material.CHAINMAIL_HELMET ||
		item.getType() == Material.GOLD_HELMET ||
		item.getType() == Material.IRON_HELMET ||
		item.getType() == Material.LEATHER_HELMET; 
		
	}
	
	public boolean isChestplate(ItemStack item){
		return item.getType() == Material.DIAMOND_CHESTPLATE ||
		item.getType() == Material.CHAINMAIL_CHESTPLATE ||
		item.getType() == Material.GOLD_CHESTPLATE ||
		item.getType() == Material.IRON_CHESTPLATE ||
		item.getType() == Material.LEATHER_CHESTPLATE;
	}
	
	public boolean isLeggings(ItemStack item){
		return item.getType() == Material.DIAMOND_LEGGINGS ||
		item.getType() == Material.CHAINMAIL_LEGGINGS ||
		item.getType() == Material.GOLD_LEGGINGS ||
		item.getType() == Material.IRON_LEGGINGS ||
		item.getType() == Material.LEATHER_LEGGINGS;
	}
	
	public boolean isBoots(ItemStack item){
		return item.getType() == Material.DIAMOND_BOOTS ||
		item.getType() == Material.CHAINMAIL_BOOTS ||
		item.getType() == Material.GOLD_BOOTS ||
		item.getType() == Material.IRON_BOOTS ||
		item.getType() == Material.LEATHER_BOOTS;
	}
	
	public boolean isSword(ItemStack item){
		return item.getType() == Material.DIAMOND_SWORD ||
		item.getType() == Material.WOOD_SWORD ||
		item.getType() == Material.GOLD_SWORD ||
		item.getType() == Material.IRON_SWORD;
	}

}
