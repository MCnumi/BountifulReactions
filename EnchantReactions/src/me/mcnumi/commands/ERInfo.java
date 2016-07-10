package me.mcnumi.commands;

import me.mcnumi.guis.MainGUI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ERInfo implements CommandExecutor{
	
	MainGUI infoInv = new MainGUI();
	
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (command.getName().equalsIgnoreCase("erinfo")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
					infoInv.openMainInv(player);
				} else {
				}
			}
		return false;
	}
}
