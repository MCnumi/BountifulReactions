package me.mcnumi.events;

import java.util.HashMap;
import java.util.UUID;

import me.mcnumi.reactions.SmiteVSBlastProtection;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerItemConsume implements Listener {

	public HashMap<UUID, Boolean> hasPlayerDrankMilk = new HashMap<UUID, Boolean>();
	
	public void playerDrinkMilk(PlayerItemConsumeEvent event) {
		Player drinker = event.getPlayer();
		
		if (new SmiteVSBlastProtection().getIsPlayerDrinkMilk().contains(drinker.getUniqueId())) {
			if (event.getItem().equals(Material.MILK_BUCKET)) {
				hasPlayerDrankMilk.put(drinker.getUniqueId(), true);
			}
		}
	}
	
	public HashMap<UUID, Boolean> getHasPlayerDrankMilk() {
		return hasPlayerDrankMilk;		
	}
}
