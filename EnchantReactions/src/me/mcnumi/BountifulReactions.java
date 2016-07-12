package me.mcnumi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.mcnumi.commands.ERInfo;
import me.mcnumi.events.InventoryClickInfoGUI;
import me.mcnumi.events.InventoryClickMainGUI;
import me.mcnumi.events.PlayerItemConsume;
import me.mcnumi.events.StopInventoryClick;
import me.mcnumi.reactions.LootingVSProtection;
import me.mcnumi.reactions.SmiteVSBlastProtection;
import me.mcnumi.reactions.Test;
import me.mcnumi.utils.Lang;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BountifulReactions extends JavaPlugin{
	
	public static BountifulReactions plugin;
	public static YamlConfiguration LANG;
	public static File LANG_FILE;
	
	public void onEnable() {		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		loadLang();
		saveDefaultConfig();
		
		plugin = this;
		
		
		//=--------------------------------{Reactions}--------------------------------=\\
		pm.registerEvents(new SmiteVSBlastProtection(), this);
		pm.registerEvents(new LootingVSProtection(), this);
		//=--------------------------------{Reactions}--------------------------------=\\
		
		//=--------------------------------{Events}--------------------------------=\\
		pm.registerEvents(new StopInventoryClick(), this);
		pm.registerEvents(new InventoryClickMainGUI(), this);
		pm.registerEvents(new InventoryClickInfoGUI(), this);
		pm.registerEvents(new PlayerItemConsume(), this);
		pm.registerEvents(new Test(), this);
		//=--------------------------------{Events}--------------------------------=\\
		
		//=--------------------------------{Commands}--------------------------------=\\
		getCommand("ERInfo").setExecutor(new ERInfo());
		//=--------------------------------{Commands}--------------------------------=\\
	}
	
	public void onDisable() {
		plugin = null;
	}
	
	/**
	 * Load the lang.yml file.
	 * @return 
	 * @return The lang.yml config.
	 */
	@SuppressWarnings("deprecation")
	public YamlConfiguration loadLang() {
	    File lang = new File(getDataFolder(), "lang.yml");
	    Logger log = Bukkit.getLogger();
	    if (!lang.exists()) {
	    	
	        try {
	            getDataFolder().mkdir();
	            lang.createNewFile();
	            InputStream defConfigStream = this.getResource("lang.yml");
	            if (defConfigStream != null) {
	                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	                defConfig.save(lang);
	                Lang.setFile(defConfig);
	                return defConfig;
	            }
	        } catch(IOException e) {
	            e.printStackTrace(); // So they notice
	            log.severe("Couldn't create language file.");
	            log.severe("This is a fatal error. Now disabling");
	            this.setEnabled(false); // Without it loaded, we can't send them messages
	        }
	    }
	    YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
	    for(Lang item:Lang.values()) {
	        if (conf.getString(item.getPath()) == null) {
	            conf.set(item.getPath(), item.getDefault());
	        }
	    }
	    Lang.setFile(conf);
	    BountifulReactions.LANG = conf;
	    BountifulReactions.LANG_FILE = lang;
	    try {
	        conf.save(getLangFile());
	    } catch(IOException e) {
	        log.log(Level.WARNING, "PluginName: Failed to save lang.yml.");
	        log.log(Level.WARNING, "PluginName: Report this stack trace to MCnumi.");
	        e.printStackTrace();
	    }
		return conf;
	}
	
	/**
	* Gets the lang.yml config.
	* @return The lang.yml config.
	*/
	public YamlConfiguration getLang() {
	    return LANG;
	}
	 
	/**
	* Get the lang.yml file.
	* @return The lang.yml file.
	*/
	public File getLangFile() {
	    return LANG_FILE;
	}
}
