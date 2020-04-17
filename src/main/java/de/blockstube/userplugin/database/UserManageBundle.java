package de.blockstube.userplugin.database;

/**
 * @author schlingeldrops
 * This class bundle the loader and saver class.
 */
public class UserManageBundle {

    private final UserLoader loader;
    private final UserSaver saver;

    public UserManageBundle(UserLoader loader, UserSaver saver) {
        this.loader = loader;
        this.saver = saver;
    }

    /**
     * @return The loader of the bundle.
     */
    public UserLoader getLoader() {
        return loader;
    }

    /**
     * @return The saver of the bundle.
     */
    public UserSaver getSaver() {
        return saver;
    }
}
