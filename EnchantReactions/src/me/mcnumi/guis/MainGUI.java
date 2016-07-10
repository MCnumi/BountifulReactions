package me.mcnumi.guis;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainGUI{
	
	private ItemStack createItem(Material material, int amount, String name, String desc) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(desc));
        item.setItemMeta(meta);
        return item;
    }
	private Inventory mainInv;
	
	
	//=--------------------------------{COMING SOON}--------------------------------=\\
	private ItemStack comingSoon = createItem(Material.SUGAR, 1, ChatColor.GREEN + "" + 
								   ChatColor.BOLD + ChatColor.UNDERLINE + "Coming Soon...", "");
	//=--------------------------------{COMING SOON}--------------------------------=\\
	
	//=--------------------------------{FILLER}--------------------------------=\\
	private ItemStack filler = createItem(Material.STAINED_GLASS_PANE, 1, "", "");
	//=--------------------------------{FILLER}--------------------------------=\\
	
	//=--------------------------------{INFO}--------------------------------=\\
	private ItemStack info = createItem(Material.TRIPWIRE_HOOK, 1, ChatColor.GOLD + "" + 
						ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Reaction Info", 
						ChatColor.BLUE + ">>" + 
	                    ChatColor.GRAY + "" + ChatColor.BOLD + "Click for info" + 
						ChatColor.RESET + "" + ChatColor.BLUE + "<<");
	//=--------------------------------{INFO}--------------------------------=\\
	public void openMainInv(Player player) {
		mainInv = Bukkit.createInventory(null, 27, ChatColor.RED + "" + ChatColor.BOLD + "" + "Reaction Main Menu");
		ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
	    /* '-' = filler
	     * '0' = item 
	     *  --- 0-0 ---
	     * | 0 | 0 | 0 |
	     *  --- 0-0 ---
	     */ 
		mainInv.setItem(0, filler);
		mainInv.setItem(1, filler);
		mainInv.setItem(2, filler);
		mainInv.setItem(3, comingSoon);
		mainInv.setItem(4, filler);
		mainInv.setItem(5, comingSoon);
		mainInv.setItem(6, filler);
		mainInv.setItem(7, filler);
	    mainInv.setItem(8, filler);
	    mainInv.setItem(9, filler);
	    mainInv.setItem(10, comingSoon);
	    mainInv.setItem(11, filler);
	    mainInv.setItem(12, filler);
	    mainInv.setItem(14, filler);
	    mainInv.setItem(15, filler);
	    mainInv.setItem(16, comingSoon);
	    mainInv.setItem(17, filler);
	    mainInv.setItem(18, filler);
	    mainInv.setItem(19, filler);
	    mainInv.setItem(20, filler);
	    mainInv.setItem(21, comingSoon);
	    mainInv.setItem(22, filler);
	    mainInv.setItem(23, comingSoon);
	    mainInv.setItem(24, filler);
	    mainInv.setItem(25, filler);
	    mainInv.setItem(26, filler);
	    mainInv.setItem(13, info);
	    player.openInventory(mainInv);
	}

	public ItemStack getInfoItem() {
		return info;
	}

}
