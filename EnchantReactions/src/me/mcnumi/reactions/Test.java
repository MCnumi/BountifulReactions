package me.mcnumi.reactions;


import java.util.HashMap;
import java.util.Random;

import me.mcnumi.EnchantifulReactions;
import me.mcnumi.utils.DamageeInfo;
import me.mcnumi.utils.DamagerInfo;
import me.mcnumi.utils.Lang;
import me.mcnumi.utils.PacketUtils;
import net.minecraft.server.v1_9_R2.EnumParticle;
import net.minecraft.server.v1_9_R2.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Test implements Listener{
	
	public HashMap<String, Long> testCooldowns = new HashMap<String, Long>();
	Random ran = new Random();
	int playerChance = ran.nextInt(EnchantifulReactions.plugin.getConfig().getInt("Reaction-chance.Looting-VS-Protection"));
	DamagerInfo damagerInfo = new DamagerInfo();
	DamageeInfo damageeInfo = new DamageeInfo();
	PacketUtils packetUtils = new PacketUtils();
	
	@EventHandler
	public boolean onDamage(EntityDamageByEntityEvent e) {
	if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-reactions.Test")) {
		
		if (e.getDamager() instanceof Player) {
			Player damager = (Player) e.getDamager();
			ItemStack damagerWeapon = damagerInfo.getDamagerHeldItem(damager);
			Location damagerLoc = damager.getLocation();
			Entity damagee = e.getEntity();
			Location damageeLoc = damagee.getLocation();	
			if (damagerWeapon.containsEnchantment(Enchantment.KNOCKBACK)) {
				
				if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-cooldowns.Test")) {
				
				int cooldownTime = EnchantifulReactions.plugin.getConfig().getInt("Cooldown-times.Test");
				if(testCooldowns.containsKey(damager.getName())) {
				 
				long secondsLeft = ((testCooldowns.get(damager.getName())/1000) + cooldownTime) - (System.currentTimeMillis()/1000);

			if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-actionbar.Test")) {	
				if(secondsLeft>0) {
					packetUtils.sendActionBar(damager, Lang.SHADOW_STEP_COOLDOWN.toString().replace("%s", Long.toString(secondsLeft)));
				return true;
				}
				
			} else if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-chat.Test")) {
				if(secondsLeft>0) {
					damager.sendMessage(Lang.SHADOW_STEP_COOLDOWN.toString().replace("%s", Long.toString(secondsLeft)));
				return true;
				}
			}
				}
				
				testCooldowns.put(damager.getName(), System.currentTimeMillis());
				} if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-chance.Test") ||
						EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-cooldowns.Test")) {
		        
					if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-cooldowns.Test") || 
							playerChance == ran.nextInt(
							EnchantifulReactions.plugin.getConfig().getInt("Reaction-chance.Test"))) {
				
			if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-particles.Test")) {
				
				new BukkitRunnable() {		
					double phi = 0;
					Location particleLoc = damagerLoc; 
					@Override
					public void run() {
						phi += Math.PI/10;
						for (double theta = 0; theta <= 2*Math.PI; theta += Math.PI/40) {							
							double r = 1.5;
							double x = r*Math.cos(theta);
							double y = r*Math.cos(phi)*-1;
							double z = r*Math.sin(theta);
							particleLoc.add(x,y,z);
							PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
									EnumParticle.SMOKE_NORMAL, true, (float) particleLoc.getX(), (float) particleLoc.getY(),
									(float) particleLoc.getZ(), 0, 0, 0, 0, 2, null);	        
							
		                    	for (Entity p : damagerLoc.getWorld().getNearbyEntities(
		                    			damagerLoc, (float) particleLoc.getX(), (float) particleLoc.getY(),
		                    			(float) particleLoc.getZ())) { //fix this
		                    		
		                    		if (p instanceof Player) { 
		                    		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet); 
		                    		} 
		                    	}							
		                    	damagerLoc.subtract(x,y,z);
						}
						
						if (phi > 1*Math.PI) {
							this.cancel();
						}
						
					}
				}.runTaskTimer(EnchantifulReactions.plugin, 0, 1);
			}
				 				 
				
		        double newX;
            	double newZ;
            	float nang = damageeLoc.getYaw() + 90;
            	
            	if(nang < 0) nang += 360;
            	
            	newX = Math.cos(Math.toRadians(nang));
            	newZ = Math.sin(Math.toRadians(nang));
   
            	Location newDamagerLoc = new Location(damageeLoc.getWorld(), damageeLoc.getX() - newX,
            		damageeLoc.getY(), damageeLoc.getZ() - newZ, damageeLoc.getYaw(), damageeLoc.getPitch());           	
                           
            	damager.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 1));
			    damager.teleport(newDamagerLoc);
			    
			    if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-actionbar.Test")) {	
				packetUtils.sendActionBar(damager,Lang.SHADOW_STEP_ACTIVATED.toString());	
				
			    } else if (EnchantifulReactions.plugin.getConfig().getBoolean("Enabled-chat.Test")) {
			    	damager.sendMessage(Lang.SHADOW_STEP_ACTIVATED.toString());
			    }
					}
				} 
				}
			}
		}
	return false; 
	}
}
