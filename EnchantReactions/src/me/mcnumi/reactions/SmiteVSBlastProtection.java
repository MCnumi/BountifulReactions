package me.mcnumi.reactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.mcnumi.BountifulReactions;
import me.mcnumi.events.PlayerItemConsume;
import me.mcnumi.utils.Cooldowns;
import me.mcnumi.utils.DamageeInfo;
import me.mcnumi.utils.DamagerInfo;
import me.mcnumi.utils.Lang;
import me.mcnumi.utils.PacketUtils;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SmiteVSBlastProtection implements Listener {
	
	private HashMap<String, Long> smiteVsBlastCooldowns = new HashMap<String, Long>();
	private HashMap<String, Long> smiteVsBlastCountdowns = new HashMap<String, Long>();
	private HashMap<String, Integer> playerSchedule = new HashMap<String, Integer>();
	private List<UUID> isPlayerDrinkMilk= new ArrayList<UUID>();
	
	Random ran = new Random();
	private int playerChance = ran.nextInt(10);
	private boolean playerDrankMilk = false;
	
	PlayerItemConsume playerItemConsume = new PlayerItemConsume();
	PacketUtils packetUtils = new PacketUtils();
	Cooldowns cooldowns = new Cooldowns(smiteVsBlastCooldowns, 
			BountifulReactions.plugin.getConfig().getInt(
						"Cooldown-times.Smite-VS-BlastProtection"));
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public boolean onDamage(EntityDamageByEntityEvent e) {
		
	if (BountifulReactions.plugin.getConfig().getBoolean("Enabled-reactions.Smite-VS-BlastProtection"))	{
		
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			
			// =--------------------------Damagee/Damager Variables--------------------------=\\
			Player damager = (Player) e.getDamager();
			ItemStack damagerWeapon = DamagerInfo.getDamagerHeldItem(damager);
			String damagerName = damager.getName();
			Player damagee = (Player) e.getEntity();
			ItemStack damageeHelmet = DamageeInfo.getDamageeHelmet(damagee);
			ItemStack damageeChestplate = DamageeInfo.getDamageeChestplate(damagee);
			ItemStack damageeLeggings = DamageeInfo.getDamageeLeggings(damagee);
			ItemStack damageeBoots = DamageeInfo.getDamageeBoots(damagee);
			// =--------------------------Damagee/Damager Variables--------------------------=\\
					
				if (DamagerInfo.isSword(damagerWeapon)
						&& DamageeInfo.isHelmet(damageeHelmet)
						|| DamageeInfo.isChestplate(damageeChestplate)
						|| DamageeInfo.isLeggings(damageeLeggings)
						|| DamageeInfo.isBoots(damageeBoots)) {
					
						if (DamagerInfo.isItemEnchanted(damagerWeapon, Enchantment.DAMAGE_UNDEAD) &&
						    DamageeInfo.isItemEnchanted(damageeHelmet, Enchantment.PROTECTION_EXPLOSIONS) ||
							DamageeInfo.isItemEnchanted(damageeChestplate, Enchantment.PROTECTION_EXPLOSIONS) ||
							DamageeInfo.isItemEnchanted(damageeLeggings, Enchantment.PROTECTION_EXPLOSIONS) ||
							DamageeInfo.isItemEnchanted(damageeBoots, Enchantment.PROTECTION_EXPLOSIONS)) {
							
							if (damagerWeapon.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) > 
							    damageeChestplate.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS)) {
						
								/*
								 * 
								 * COOLDOWN TIMING
								 * 
								 */
								
								if(BountifulReactions.plugin.getConfig().getBoolean(
										"Enabled-cooldowns.Smite-VS-BlastProtection")) {
																	 								 
									
									if(cooldowns.isPlayerCooldown(damagerName)) {
								 
										long cooldownSecondsLeft = cooldowns.getSecondsleft((damagerName));										
										
											if (BountifulReactions.plugin.getConfig().getBoolean(
													"Enabled-actionbar.Smite-VS-BlastProtection")) {
												
												if(cooldownSecondsLeft>0) {
													
													packetUtils.sendActionBar(damager,
															Lang.TIME_IMPLOSION_COOLDOWN.toString().replace(
																	"%s", Long.toString(cooldownSecondsLeft)));
													
													return true;
												}
												
										 } else if (BountifulReactions.plugin.getConfig().getBoolean(
												 "Enabled-chat.Smite-VS-BlastProtection")) {
											 
											 	if(cooldownSecondsLeft>0) {
									
											 		damager.sendMessage(
											 				Lang.TIME_IMPLOSION_COOLDOWN.toString().replace(
											 						"%s", Long.toString(cooldownSecondsLeft)));
									
											 		return true;
											 	}
										 }
									} cooldowns.setPlayerCooldown(damagerName);
									
									/*
									 * 
									 * COOLDOWN TIMING
									 * 
									 */
								
								
								
								} if (BountifulReactions.plugin.getConfig().getBoolean(
										"Enabled-chance.Smite-VS-BlastProtection") ||
										
										BountifulReactions.plugin.getConfig().getBoolean(
												"Enabled-cooldowns.Smite-VS-BlastProtection")) {
									
									if (BountifulReactions.plugin.getConfig().getBoolean(
											"Enabled-cooldowns.Smite-VS-BlastProtection") ||
											
											playerChance == ran.nextInt(
											BountifulReactions.plugin.getConfig().getInt(
													"Reaction-chance.Smite-VS-BlastProtection"))) {
										
										damagee.addPotionEffect(new PotionEffect(
												PotionEffectType.GLOWING, 
												BountifulReactions.plugin.getConfig().getInt(
														"Timed-implosion.Implosion-countdown")*20,
												1));
										
										isPlayerDrinkMilk.add(damagee.getUniqueId());
										
										
										/*
										 * 
										 * COUNTDOWN TIMING
										 * 
										 */	
						if(BountifulReactions.plugin.getConfig().getBoolean(
											"Timed-Implosion.Enabled-countdown")) {
										
						int countdownTime = BountifulReactions.plugin.getConfig().getInt(
										"Timed-implosion.Implosion-countdown");
											
						if(smiteVsBlastCountdowns.containsKey(damagee.getName())) {
										 
							long countdownSecondsLeft = ((
								smiteVsBlastCountdowns.get(damagee.getName())/1000) +
								countdownTime) -
								(System.currentTimeMillis()/1000);
							
							/*
							 * 
							 * ACTION BAR COUNTDOWN
							 * 
							 */	
							
								if (BountifulReactions.plugin.getConfig().getBoolean(
									"Enabled-actionbar.Smite-VS-BlastProtection")) {
									
									if(countdownSecondsLeft>0) {
										
										int countdownTask = 
											Bukkit.getScheduler().scheduleSyncRepeatingTask(
											BountifulReactions.plugin, new BukkitRunnable() {

											@Override
											public void run() {
												
												while (countdownSecondsLeft>0) {
													
													if (BountifulReactions.plugin.getConfig().getBoolean(
														"Enabled-actionbar.Smite-VS-BlastProtection")) {
														
														packetUtils.sendActionBar(damagee,
																Lang.TIME_IMPLOSION_COUNTDOWN.toString().replace(
																 "%s", Long.toString(countdownSecondsLeft)));
														
																	}
																}
													if (countdownSecondsLeft<0) {
																		
														Bukkit.getScheduler().cancelTask(
														playerSchedule.get(damagee.getName()));
														
													    playerSchedule.remove(damagee.getName());
																		}
																	}
															    	  
															      },20,20);
										
															playerSchedule.put(damagee.getName(), countdownTask);
														}
									
									/*
									 * 
									 * ACTION BAR COUNTDOWN
									 * 
									 */	
									
									/*
									 * 
									 * CHAT COUNTDOWN
									 * 
									 */	
									
														
												 } else if (BountifulReactions.plugin.getConfig().getBoolean(
														 "Enabled-chat.Smite-VS-BlastProtection")) {
													 
													 	if(countdownSecondsLeft>0) {
													 		
											int countdownTask = 
													 Bukkit.getScheduler().scheduleSyncRepeatingTask(
													 BountifulReactions.plugin, new BukkitRunnable() {

													@Override
													public void run() {
														
													while (countdownSecondsLeft>0) {
														
													 if (BountifulReactions.plugin.getConfig().getBoolean(
																"Enabled-chat.Smite-VS-BlastProtection")) {	
														 
														damagee.sendMessage(
												 				Lang.TIME_IMPLOSION_COUNTDOWN.toString().replace(
												 						"%s", Long.toString(countdownSecondsLeft)));
													 }
													}
														if (countdownSecondsLeft<0) {
															 Bukkit.getScheduler().
															 cancelTask(playerSchedule.get(damagee.getName()));
															 
									                         playerSchedule.remove(damagee.getName());
														}
													}
											    	  
											      },20,20);
											playerSchedule.put(damagee.getName(), countdownTask);
													 		
													 	}
												 }
								
								
								/*
								 * 
								 * CHAT COUNTDOWN
								 * 
								 */	
								
								
											}
						
											smiteVsBlastCountdowns.put(
													damagee.getName(),
													System.currentTimeMillis());
										}
										/*
										 * 
										 * COUNTDOWN TIMING
										 * 
										 */
										
										
										
										
										
										/*
										 * 
										 * Check if the player drank milk.
										 * if true, set global variable "playerDrankMilk"
										 * to true.
										 * 
										 */	
										
										new BukkitRunnable() {
											int endLoop;
											@Override
											public void run() {
												if (playerItemConsume.getHasPlayerDrankMilk().get(damagee.getUniqueId())) {
													playerDrankMilk = true;
													
												}
												endLoop++;
												
											if (endLoop >= BountifulReactions.plugin.getConfig().getInt(
													"Timed-implosion.Implosion-countdown"))
												cancel();
											}
											
										}.runTaskTimer(BountifulReactions.plugin, 0, 20);
										
										
										/*
										 * If the damagee did not drink milk within the
										 * given time, he will be struck by lightning,
										 * doing X amount of damage. 
										 */	
											
									 if (! playerDrankMilk) {
										 damagee.getWorld().strikeLightningEffect(damagee.getLocation());
										 damagee.damage(BountifulReactions.plugin.getConfig().getInt(
													"Timed-implosion.Damage"));
										 
										 playerItemConsume.getHasPlayerDrankMilk().remove(damagee.getUniqueId());
										 isPlayerDrinkMilk.remove(damagee.getUniqueId());
									 	}
									}
								}
							}
						}
					}
				}
			}
		return false;
	}
	
	public List<UUID> getIsPlayerDrinkMilk() {
		return isPlayerDrinkMilk;		
	}
}

