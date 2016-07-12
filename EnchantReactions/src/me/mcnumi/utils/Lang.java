package me.mcnumi.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
 
/**
* An enum for requesting strings from the language file.
* @author gomeow
*/
public enum Lang {
	PREFIX("Prefix", "&aE&8R "), 
	PLAYER_ONLY("Player-only", "&4This command is only usable by players!"),
    SHADOW_STEP_ACTIVATED("Shadow-Step", "&a&lShadow-Step activated"),
    SHADOW_STEPPED("Shadow-Stepped", "&c&lYou've been Shadow-Stepped!"),
    SHADOW_STEP_COOLDOWN("Shadow-Step-cooldown", "&cYou can't use &a&lShadow-Step &r&cfor another %s seconds!"),
    TIMED_IMPLOSION("Timed-Implosion","&a&lTimed-Implosion activated"),
    TIME_IMPLODED("Time-Imploded","&c&lYou've been Time-Imploded!"),
    TIME_IMPLOSION_COUNTDOWN("Timed-Implosion-countdown", "You have %s to drink a milk bucket before you get struck by lighting!"),
    TIME_IMPLOSION_COOLDOWN("Timed-Implosion-cooldown","&cYou can't use &a&lTimed-Implosion &r&cfor another %s seconds!");
 
    private String path;
    private String def;
    private static YamlConfiguration LANG;
 
    /**
    * Lang enum constructor.
    * @param path The string path.
    * @param start The default string.
    */
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }
 
    /**
    * Set the {@code YamlConfiguration} to use.
    * @param config The config to set.
    */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }
 
    @Override
    public String toString() {
            return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def)) + " ";
    }
 
    /**
    * Get the default value of the path.
    * @return The default value of the path.
    */
    public String getDefault() {
        return this.def;
    }
 
    /**
    * Get the path to the string.
    * @return The path to the string.
    */
    public String getPath() {
        return this.path;
    }
}
