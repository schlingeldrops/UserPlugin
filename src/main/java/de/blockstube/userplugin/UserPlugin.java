package de.blockstube.userplugin;

import de.blockstube.userplugin.command.CoinsCommand;
import de.blockstube.userplugin.command.TimerCommand;
import de.blockstube.userplugin.command.UserCommand;
import de.blockstube.userplugin.database.DatabaseConfiguration;
import de.blockstube.userplugin.database.DatabaseType;
import de.blockstube.userplugin.inventory.UserInventory;
import de.blockstube.userplugin.listener.UserDataListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author schlingeldrops
 * This is a plugin to manage the users.
 */
public class UserPlugin extends JavaPlugin {

    private final UserInventory userInventory = new UserInventory();

    private final CoinsCommand coinsCommand = new CoinsCommand();
    private final TimerCommand timerCommand = new TimerCommand(this);
    private final UserCommand userCommand = new UserCommand(userInventory);

    private final UserDataListener userDataListener = new UserDataListener();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        final DatabaseConfiguration configuration = new DatabaseConfiguration(
                getConfig().getString("database.host"),
                getConfig().getInt("database.port"),
                getConfig().getString("database.database"),
                getConfig().getString("database.username"),
                getConfig().getString("database.password")
        );

        UserSystem.createInstance(DatabaseType.valueOf(getConfig().getString("database.type").toUpperCase()).getManageBundle(configuration));

        userInventory.enable(this);

        Bukkit.getPluginCommand("coins").setExecutor(coinsCommand);
        Bukkit.getPluginCommand("timer").setExecutor(timerCommand);
        Bukkit.getPluginCommand("user").setExecutor(userCommand);

        Bukkit.getPluginManager().registerEvents(userDataListener, this);

        Bukkit.getOnlinePlayers().forEach(player -> UserSystem.getInstance().initializeUser(player));
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> UserSystem.getInstance().saveUser(player));

        HandlerList.unregisterAll(userDataListener);

        Bukkit.getPluginCommand("coins").setExecutor(null);
        Bukkit.getPluginCommand("timer").setExecutor(null);
        Bukkit.getPluginCommand("user").setExecutor(null);

        userInventory.disable();
    }
}
