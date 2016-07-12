package me.mcnumi.utils;

import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketUtils {
	
	/**
	 * 
	 * Sends an action-bar to specified player.
	 * @param player Player to send the action-bar to.
	 * @param msg Message to be sent.
	 */
	public void sendActionBar(Player player, String msg) {
      String s = ChatColor.translateAlternateColorCodes('&', msg);
      IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + "\"}");
      PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
      ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
    }
    
}
