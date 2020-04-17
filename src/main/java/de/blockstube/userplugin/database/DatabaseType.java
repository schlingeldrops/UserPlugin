package de.blockstube.userplugin.database;

import de.blockstube.userplugin.database.mongo.MongoDatabase;
import de.blockstube.userplugin.database.mongo.MongoUserLoader;
import de.blockstube.userplugin.database.mongo.MongoUserSaver;

/**
 * @author schlingeldrops
 * These are the supported databases to load and save user objects.
 */
public enum DatabaseType {

    MONGO_DB() {
        @Override
        public UserManageBundle getManageBundle(DatabaseConfiguration configuration) {
            final MongoDatabase database = new MongoDatabase(configuration);
            return new UserManageBundle(
                    new MongoUserLoader(database),
                    new MongoUserSaver(database)
            );
        }
    };

    /**
     * Give you the manage bundle with the loader and saver.
     * @param configuration The configuration of the database.
     * @return The manage bundle.
     */
    public abstract UserManageBundle getManageBundle(DatabaseConfiguration configuration);

}
