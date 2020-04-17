package de.blockstube.userplugin;

import de.blockstube.userplugin.database.UserLoader;
import de.blockstube.userplugin.database.UserManageBundle;
import de.blockstube.userplugin.database.UserSaver;
import de.blockstube.userplugin.exception.UserNotExistsException;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author schlingeldrops
 * This is a user system with manage the users.
 */
public final class UserSystem {

    private static UserSystem INSTANCE;

    /**
     * This method alloy you to get the instance of this class.
     *
     * @return
     */
    public static UserSystem getInstance() {
        return INSTANCE;
    }

    /**
     * With this method you can create an user system instance,
     * this method is ony allowed to use by the user plugin.
     * @param manageBundle The bundle to manage the system.
     * @return The created system.
     */
    static UserSystem createInstance(UserManageBundle manageBundle) {
        return createInstance(manageBundle.getLoader(), manageBundle.getSaver());
    }

    /**
     * With this method you can create an user system instance,
     * this method is ony allowed to use by the user plugin.
     * @param loader The loader to load user objects.
     * @param saver The saver to save user objects.
     * @return The created system.
     */
    static UserSystem createInstance(UserLoader loader, UserSaver saver) {
        return new UserSystem(loader, saver);
    }

    private final Map<UUID, User> userMap = new HashMap<UUID, User>();

    private final UserLoader userLoader;
    private final UserSaver userSaver;

    /**
     * This constructor is private to prevent instantiation from the outside.
     * It's not allowed to use them.
     */
    private UserSystem(UserLoader userLoader, UserSaver userSaver) {
        this.userLoader = userLoader;
        this.userSaver = userSaver;

        INSTANCE = this;
    }

    /**
     * This method create a user object and save it in the database.
     * @param player The player with they are create the user object.
     * @return The user object after creation.
     */
    public User createUser(Player player) {
        final User user = new User(player);
        this.userSaver.save(player, user);
        return user;
    }

    /**
     * Load the given player in a user object.
     * @param player The player to load.
     * @return The user object.
     */
    public User loadUser(Player player) {
        return this.userLoader.load(player);
    }

    /**
     * It's create and save a new user instance when it dose not exists,
     * else it load it from the database.
     *
     * The final user instance are registered in the cache and can be called by {@link #getUser(Player)}.
     * @param player The player to initialize.
     * @return The loaded or created user instance.
     */
    public User initializeUser(Player player) {
        synchronized (userMap) {
            User user;

            try {
                user = userLoader.load(player);
            } catch (UserNotExistsException ignore) {
                user = createUser(player);
            }

            userMap.put(player.getUniqueId(), user);

            return user;
        }
    }

    /**
     * With this method you can remove the user data from the cache.
     * @param player
     */
    public void unloadUser(Player player) {
        synchronized (userMap) {
            userMap.remove(player.getUniqueId());
        }
    }

    /**
     * Save the user data.
     * @param player The player to save.
     */
    public void saveUser(Player player) {
        synchronized (userMap) {
            userSaver.save(player, getUser(player));
        }
    }

    /**
     * With this method you can delete any user data.
     * @param player The player.
     */
    public void deleteUser(Player player) {
        synchronized (userMap) {
            userMap.remove(player.getUniqueId());
        }
    }

    /**
     * It give you the user data of a player from the cache.
     * @param player The player.
     * @return The user data from cash.
     */
    public User getUser(Player player) {
        synchronized (userMap) {
            return userMap.get(player.getUniqueId());
        }
    }

    /**
     * @param player The player to check.
     * @return It's true when user data can be found in the cache.
     */
    public boolean existsUser(Player player) {
        synchronized (userMap) {
            return userMap.containsKey(player.getUniqueId());
        }
    }

}
