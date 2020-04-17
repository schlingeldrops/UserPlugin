package de.blockstube.userplugin.listener;

import de.blockstube.userplugin.User;
import de.blockstube.userplugin.UserSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author schlingeldrops
 * This is the user data listener, this register and save the player data.
 */
public class UserDataListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        final User user = UserSystem.getInstance().initializeUser(event.getPlayer());
        user.setLastLoginToNow();
        event.getPlayer().sendMessage(user.toString());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent event) {
        UserSystem.getInstance().saveUser(event.getPlayer());
    }

}
