package de.blockstube.userplugin.database;

import de.blockstube.userplugin.User;
import org.bukkit.entity.Player;

/**
 * @author schlingeldrops
 * This is a user saver, it save from a player the user object.
 */
public abstract class UserSaver<T extends Database> {

    private final T database;

    public UserSaver(T database) {
        this.database = database;
    }

    /**
     * @return The database to save the user data.
     */
    public T getDatabase() {
        return database;
    }

    /**
     * With this method you can save a user object from a player.
     * @param player The player.
     * @param user The user object to save.
     */
    public abstract void save(Player player, User user);

}
