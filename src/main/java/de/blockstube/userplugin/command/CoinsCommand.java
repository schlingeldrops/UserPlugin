package de.blockstube.userplugin.command;

import de.blockstube.userplugin.UserSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author schlingeldrops
 * This is a coins command, this use the {@link de.blockstube.userplugin.UserSystem}.
 */
public class CoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length < 2) {
            sender.sendMessage("§e/coins <Spieler> add <Anzahl>");
            sender.sendMessage("§e/coins <Spieler> remove <Anzahl>");
            sender.sendMessage("§e/coins <Spieler> set <Anzahl>");
            sender.sendMessage("§e/coins <Spieler> get");
            return true;
        }

        final Player player = Bukkit.getPlayer(args[0]);

        if (args[1].equalsIgnoreCase("add")) {
            if (args.length != 3) {
                sender.sendMessage("§e/coins <Spieler> add <Anzahl>");
                return true;
            }

            final int coins;

            try {
                coins = Integer.valueOf(args[2]);
            } catch (Exception e) {
                sender.sendMessage("§c/coins <Spieler> add <Anzahl>");
                return true;
            }

            try {
                UserSystem.getInstance().getUser(player).addCoins(coins);
                sender.sendMessage(String.format("§7Der Spieler §c%s §7hat §6§l%d §7Coins erhalten", player.getDisplayName(), coins));
                player.sendMessage(String.format("§7Du hast §6§l%d §7Coins erhalten", coins));
            } catch (Exception e) {
                sender.sendMessage("§cEs ist leider ein Fehler aufgetreten.");
                e.printStackTrace();
                return true;
            }
            return true;
        }

        if (args[1].equalsIgnoreCase("remove")) {
            if (args.length != 3) {
                sender.sendMessage("§e/coins <Spieler> remove <Anzahl>");
                return true;
            }

            final int coins;

            try {
                coins = Integer.valueOf(args[2]);
            } catch (Exception e) {
                sender.sendMessage("§c/coins <Spieler> remove <Anzahl>");
                return true;
            }

            try {
                UserSystem.getInstance().getUser(player).removeCoins(coins);
                sender.sendMessage(String.format("§7Dem Spieler §c%s §7wurden §6§l%d §7Coins entfernt", player.getDisplayName(), coins));
                player.sendMessage(String.format("§7Dir wurden §6§l%d §7Coins entfernt", coins));
            } catch (Exception e) {
                sender.sendMessage("§cEs ist leider ein Fehler aufgetreten.");
                e.printStackTrace();
                return true;
            }
            return true;
        }

        if (args[1].equalsIgnoreCase("set")) {
            if (args.length != 3) {
                sender.sendMessage("§e/coins <Spieler> set <Anzahl>");
                return true;
            }

            final int coins;

            try {
                coins = Integer.valueOf(args[2]);
            } catch (Exception e) {
                sender.sendMessage("§c/coins <Spieler> set <Anzahl>");
                return true;
            }

            try {
                UserSystem.getInstance().getUser(player).setCoins(coins);
                sender.sendMessage(String.format("§7Die Coins vom Spieler §c%s §7wurden auf §6§l%d §7gesetzt", player.getDisplayName(), coins));
                player.sendMessage(String.format("§7Deine Coins wurden auf §6§l%d §7gesetzt", coins));
            } catch (Exception e) {
                sender.sendMessage("§cEs ist leider ein Fehler aufgetreten.");
                e.printStackTrace();
                return true;
            }
            return true;
        }

        if (args[1].equalsIgnoreCase("get")) {
            if (args.length != 2) {
                sender.sendMessage("§e/coins <Spieler> get");
                return true;
            }

            try {
                sender.sendMessage(String.format("§7Der Spieler §c%s §7hat §6§l%d §7Coins", player.getDisplayName(), UserSystem.getInstance().getUser(player).getCoins()));
            } catch (Exception e) {
                sender.sendMessage("§cEs ist leider ein Fehler aufgetreten.");
                e.printStackTrace();
                return true;
            }
            return true;
        }

        else {
            sender.sendMessage("§e/coins <Spieler> add <Anzahl>");
            sender.sendMessage("§e/coins <Spieler> remove <Anzahl>");
            sender.sendMessage("§e/coins <Spieler> set <Anzahl>");
            sender.sendMessage("§e/coins <Spieler> get");
            return true;
        }

    }
}
