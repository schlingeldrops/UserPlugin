package de.blockstube.userplugin.database;

/**
 * @author schlingeldrops
 * With this class you can call to a database and link it to a user loader and saver.
 */
public abstract class Database {

    private final DatabaseConfiguration configuration;

    public Database(DatabaseConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * @return The database connection configuration.
     */
    public DatabaseConfiguration getConfiguration() {
        return configuration;
    }
}
