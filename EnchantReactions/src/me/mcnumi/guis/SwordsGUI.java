package me.mcnumi.guis;

import java.util.Arrays;

import me.mcnumi.BountifulReactions;
import me.mcnumi.utils.EnchantGlow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SwordsGUI {
	private Inventory swordsInv;
	
	private ItemStack createItem(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

	//=--------------------------------{Smite Vs Blast Protection}--------------------------------=\\	
	private ItemStack smiteVsBlastProtection = 
			createItem(Material.BOOK, 1, ChatColor.AQUA + "" + ChatColor.BOLD + "Smite Vs Blast Protection");
	ItemMeta metaSmiteBlast = smiteVsBlastProtection.getItemMeta();
	//=--------------------------------{Smite Vs Blast Protection}--------------------------------=\\
	
	//=--------------------------------{Looting Vs Protection}--------------------------------=\\
	private ItemStack lootingVsProtection = 
			createItem(Material.BOOK, 1, ChatColor.AQUA + "" + ChatColor.BOLD + "Looting Vs Protection");
	ItemMeta metaLootProt = lootingVsProtection.getItemMeta();
	//=--------------------------------{Looting Vs Protection}--------------------------------=\\
	
	public void openSwordsInv(Player p) {
		swordsInv = Bukkit.createInventory(null, 27, ChatColor.RED + "" + ChatColor.BOLD + "" + "Swords Reaction Info");
		//=--------------------------------{Smite Vs Blast Protection}--------------------------------=\\
		if (BountifulReactions.plugin.getConfig().getBoolean("Enabled-cooldowns.Smite-VS-BlastProtection")) {
		metaSmiteBlast.setLore(Arrays.asList(
				ChatColor.DARK_AQUA + "Timed-Implosion:",
				"",
				ChatColor.GRAY + "If the level of Smite is greater than Blast Protection,",
				ChatColor.GRAY + "the player with Blast Protection will become charged",
				ChatColor.GRAY + "and struck by lighting if he does not drink milk within",
				ChatColor.GRAY + String.valueOf(
						BountifulReactions.plugin.getConfig().getInt("Timed-implosion.Implosion-countdown")) +
						" seconds! "
				+ "Dealing " + String.valueOf(
						BountifulReactions.plugin.getConfig().getInt("Timed-implosion.Damage")) +
				" damage.",
				"",
				ChatColor.DARK_AQUA + "Cool-down:",
				ChatColor.RESET + "" + ChatColor.YELLOW + "" + 
				ChatColor.UNDERLINE +
				BountifulReactions.plugin.getConfig().getInt("Cooldown-times.Smite-VS-BlastProtection") + 
				" Seconds"));
		smiteVsBlastProtection.setItemMeta(metaSmiteBlast);
		EnchantGlow.addGlow(smiteVsBlastProtection);
		swordsInv.setItem(0, smiteVsBlastProtection);
		} else if (BountifulReactions.plugin.getConfig().getBoolean("Enabled-chance.Smite-VS-BlastProtection")) {
			metaSmiteBlast.setLore(Arrays.asList(
					ChatColor.DARK_AQUA + "Timed-Implosion:",
					"",
					ChatColor.GRAY + "If the level of Smite is greater than Blast Protection,",
					ChatColor.GRAY + "the player with Blast Protection will become charged",
					ChatColor.GRAY + "and struck by lighting if he does not drink milk within",
					ChatColor.GRAY + String.valueOf(
							BountifulReactions.plugin.getConfig().getInt("Timed-implosion.Implosion-countdown")) +
							" seconds! "
					+ "Dealing " + String.valueOf(
							BountifulReactions.plugin.getConfig().getInt("Timed-implosion.Damage")) +
					" damage.",
					"",
					ChatColor.DARK_AQUA + "Chance:",
					ChatColor.RESET + "" + ChatColor.YELLOW + "" + 
					ChatColor.UNDERLINE + "1/" +
					BountifulReactions.plugin.getConfig().getInt("Reaction-chance.Smite-VS-BlastProtection") + 
					" Chance"));
			smiteVsBlastProtection.setItemMeta(metaSmiteBlast);
			EnchantGlow.addGlow(smiteVsBlastProtection);
			swordsInv.setItem(0, smiteVsBlastProtection);
		}
		//=--------------------------------{Smite Vs Blast Protection}--------------------------------=\\
		
		//=--------------------------------{Looting Vs Protection}--------------------------------=\\
		if (BountifulReactions.plugin.getConfig().getBoolean("Enabled-cooldowns.Looting-VS-Protection")) {
		metaLootProt.setLore(Arrays.asList(
				ChatColor.DARK_AQUA + "Shadow-Step:",
				"",
				ChatColor.GRAY + "The player with Looting will instantly teleport behind",
				ChatColor.GRAY + "the player with Protection for a clean backstab.",
				ChatColor.GRAY + "However, the player with Looting will in be ",
			    ChatColor.GRAY + "easily visible in return",
				ChatColor.GRAY + "for having used Shadow-Step.",
				"",
				ChatColor.DARK_AQUA + "Cool-down:",
				ChatColor.RESET + "" + ChatColor.YELLOW + "" + 
				ChatColor.UNDERLINE +
				BountifulReactions.plugin.getConfig().getInt("Cooldown-times.Looting-VS-Protection") + 
				" Seconds"));
		lootingVsProtection.setItemMeta(metaLootProt);
		EnchantGlow.addGlow(lootingVsProtection);
		swordsInv.setItem(1, lootingVsProtection);
		
		} else if (BountifulReactions.plugin.getConfig().getBoolean("Enabled-chance.Looting-VS-Protection")) {
			metaLootProt.setLore(Arrays.asList(
					ChatColor.DARK_AQUA + "Shadow-Step:",
					"",
					ChatColor.GRAY + "The player with Looting will instantly teleport behind",
					ChatColor.GRAY + "the player with Protection for a clean backstab.",
					ChatColor.GRAY + "However, the player with Looting will in be ",
				    ChatColor.GRAY + "easily visible in return",
					ChatColor.GRAY + "for having used Shadow-Step.",
					"",
					ChatColor.DARK_AQUA + "Chance:",
					ChatColor.RESET + "" + ChatColor.YELLOW + "" + 
					ChatColor.UNDERLINE + "1/" +
					BountifulReactions.plugin.getConfig().getInt("Reaction-chance.Looting-VS-Protection") + 
					" Chance"));
			lootingVsProtection.setItemMeta(metaLootProt);
			EnchantGlow.addGlow(lootingVsProtection);
			swordsInv.setItem(1, lootingVsProtection);	
		}
		//=--------------------------------{Looting Vs Protection}--------------------------------=\\
		p.openInventory(swordsInv);
	}
}
