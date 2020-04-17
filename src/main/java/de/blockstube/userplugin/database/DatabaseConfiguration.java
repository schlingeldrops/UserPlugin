package de.blockstube.userplugin.database;

/**
 * @author schlingeldrops
 * This is the configuration of the mongodb server.
 * It's a very simple data object.
 */
public class DatabaseConfiguration {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public DatabaseConfiguration(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    /**
     * @return The database server host.
     */
    public String getHost() {
        return host;
    }

    /**
     * @return The database server port.
     */
    public int getPort() {
        return port;
    }

    /**
     * @return The database server selected database.
     */
    public String getDatabase() {
        return database;
    }

    /**
     * @return The username to authenticate.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return The password to authenticate.
     */
    public String getPassword() {
        return password;
    }
}
