package de.blockstube.userplugin.command;

import de.blockstube.userplugin.inventory.UserInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author schlingeldrops
 * This is a user command, it's to open the user inventory.
 */
public class UserCommand implements CommandExecutor {

    private final UserInventory userInventory;

    public UserCommand(UserInventory userInventory) {
        this.userInventory = userInventory;
    }

    public UserInventory getUserInventory() {
        return userInventory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst dafür ein Spieler sein.");
            return true;
        }

        userInventory.open((Player) sender);
        return true;
    }
}
