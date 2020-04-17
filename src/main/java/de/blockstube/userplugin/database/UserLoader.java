package de.blockstube.userplugin.database;

import de.blockstube.userplugin.User;
import org.bukkit.entity.Player;

/**
 * @author schlingeldrops
 * This is a user loader, it load from a player the user object.
 */
public abstract class UserLoader<T extends Database> {

    private final T database;

    public UserLoader(T database) {
        this.database = database;
    }

    /**
     * @return The database to load the user data.
     */
    public T getDatabase() {
        return database;
    }

    /**
     * With this method you can load a user object.
     * @param player The player.
     * @return The loaded user object.
     */
    public abstract User load(Player player);

}
