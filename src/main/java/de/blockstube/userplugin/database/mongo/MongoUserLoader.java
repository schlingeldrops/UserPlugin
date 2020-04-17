package de.blockstube.userplugin.database.mongo;

import com.mongodb.client.model.Filters;
import de.blockstube.userplugin.User;
import de.blockstube.userplugin.database.UserLoader;
import de.blockstube.userplugin.exception.UserNotExistsException;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author schlingeldrops
 */
public final class MongoUserLoader extends UserLoader<MongoDatabase> {

    public MongoUserLoader(MongoDatabase database) {
        super(database);
    }

    public User load(Player player) {
        final Document document = getDatabase().getCollection("users").find(Filters.eq("uuid", player.getUniqueId().toString())).first();

        if (document == null)
            throw new UserNotExistsException(String.format("The user with the uuid %s dose not exists in the database.", player.getUniqueId()));

        return new User(
                document.getString("name"),
                UUID.fromString(document.getString("uuid")),
                document.getInteger("coins"),
                document.getLong("created"),
                document.getLong("last_login")
        );
    }

}
