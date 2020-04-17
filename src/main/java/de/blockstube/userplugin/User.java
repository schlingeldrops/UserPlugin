package de.blockstube.userplugin;

import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.UUID;

/**
 * @author schlingeldrops
 * This is a user object, it contains all the data of a user.
 * This object can you save in any database and can load it.
 */
public final class User {

    private final String name;
    private final UUID uuid;
    private int coins;
    private final long created;
    private long lastLogin;

    /**
     * This is the main constructor, here you can set all variables when of the user object.
     * @param name The name of the user.
     * @param uuid The uuid of the user.
     * @param coins The start coins of the user.
     * @param created The date in mills when the user was created.
     * @param lastLogin The date in mills when the user has the last login.
     */
    public User(String name, UUID uuid, int coins, long created, long lastLogin) {
        this.name = name;
        this.uuid = uuid;
        this.coins = coins;
        this.created = created;
        this.lastLogin = lastLogin;
    }

    /**
     * With this constructor you can only create a new user and not initialize.
     * @param name The name of the user.
     * @param uuid The uuid of the user.
     * @param coins The start coins of the user.
     * @param join The join date in mills when the user was joined.
     */
    public User(String name, UUID uuid, int coins, long join) {
        this(name, uuid, coins, join, join);
    }

    /**
     * With this constructor you can only create a new user and not initialize.
     *
     * Set the join at now.
     * @param name The name of the user.
     * @param uuid The uuid of the user.
     * @param coins The start coins of the user.
     */
    public User(String name, UUID uuid, int coins) {
        this(name, uuid, coins, Calendar.getInstance().getTime().getTime());
    }

    /**
     * With this constructor you can only create a new user and not initialize.
     *
     * Set the start coins to 0.
     * @param name The name of the user.
     * @param uuid The uuid of the user.
     */
    public User(String name, UUID uuid) {
        this(name, uuid, 0);
    }

    /**
     * With this constructor you can only create a new user and not initialize.
     * However, this is the easiest way if the user has newly joined the server.
     *
     * The name and uuid are read by the player object.
     * @param player The player object.
     */
    public User(Player player) {
        this(player.getName(), player.getUniqueId());
    }

    /**
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Give you the uuid of the user.
     */
    public UUID getUniqueId() {
        return uuid;
    }

    /**
     * @return Give you the coins of the the user.
     */
    public int getCoins() {
        synchronized (uuid) {
            return coins;
        }
    }

    /**
     * With this method you can set the coins of a user.
     * @param coins The coins to set.
     */
    public void setCoins(int coins) {
        synchronized (uuid) {
            this.coins = coins;
        }
    }

    /**
     * With this method you can add coins to a user.
     * @param coins The coins to add.
     */
    public void addCoins(int coins) {
        synchronized (uuid) {
            this.coins += coins;
        }
    }

    /**
     * With this method you can remove coins from a user.
     * @param coins The coins to remove.
     */
    public void removeCoins(int coins) {
        synchronized (uuid) {
            this.coins -= coins;
        }
    }

    /**
     * @return The create date of this user object in mills.
     */
    public long getCreated() {
        return created;
    }

    /**
     * @return The last login of the user in mills.
     */
    public long getLastLogin() {
        synchronized (name) {
            return lastLogin;
        }
    }

    /**
     * With this method you can set the last login date,
     * it sets the date to the date it was when it was executed.
     */
    public void setLastLoginToNow() {
        synchronized (name) {
            this.lastLogin = Calendar.getInstance().getTime().getTime();
        }
    }

    /**
     * @return The content of the class.
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", uuid=" + uuid +
                ", coins=" + coins +
                ", created=" + created +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
