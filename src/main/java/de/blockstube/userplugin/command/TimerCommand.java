package de.blockstube.userplugin.command;

import de.blockstube.userplugin.countdown.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author schlingeldrops
 * This is a timer command, with this you can start and stop a timer.
 */
public class TimerCommand implements CommandExecutor {

    private final Countdown countdown;

    public TimerCommand(JavaPlugin javaPlugin) {
        this.countdown = new Countdown(javaPlugin, 30, () -> Bukkit.broadcastMessage("§4§lHUIIIII, LOS GEHT ES!"), 30, 20, 10, 5, 4, 3, 2, 1);
    }

    public Countdown getCountdown() {
        return countdown;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§e/timer start");
            sender.sendMessage("§e/timer stop");
            return true;
        }

        if (args[0].equalsIgnoreCase("start")) {
            if (args.length != 1) {
                sender.sendMessage("§c/timer start");
                return true;
            }
            countdown.start();
            sender.sendMessage("§7Der Timer wurde gestartet.");
            return true;
        }

        if (args[0].equalsIgnoreCase("stop")) {
            if (args.length != 1) {
                sender.sendMessage("§c/timer stop");
                return true;
            }
            countdown.stop();
            sender.sendMessage("§7Der Timer wurde gestoppt.");
            return true;
        }

        else {
            sender.sendMessage("§e/timer start");
            sender.sendMessage("§e/timer stop");
            return true;
        }
    }
}
