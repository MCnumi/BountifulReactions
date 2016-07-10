package me.mcnumi.utils;

import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketUtils {
	
	public void sendActionBar(Player p, String msg) {
      String s = ChatColor.translateAlternateColorCodes('&', msg);
      IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + "\"}");
      PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
      ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
    
}
