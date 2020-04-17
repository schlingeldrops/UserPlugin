package de.blockstube.userplugin.database.mongo;

import com.mongodb.client.model.Filters;
import de.blockstube.userplugin.User;
import de.blockstube.userplugin.database.UserSaver;
import org.bson.Document;
import org.bukkit.entity.Player;

/**
 * @author schlingeldrops
 */
public class MongoUserSaver extends UserSaver<MongoDatabase> {

    public MongoUserSaver(MongoDatabase database) {
        super(database);
    }

    public void save(Player player, User user) {
        if (user == null) {
            getDatabase().getCollection("users").deleteMany(Filters.eq("uuid", player.getUniqueId().toString()));
            return;
        }

        final Document document = new Document();

        document.append("name", user.getName());
        document.append("uuid", user.getUniqueId().toString());
        document.append("coins", user.getCoins());
        document.append("created", user.getCreated());
        document.append("last_login", user.getLastLogin());

        if (getDatabase().getCollection("users").countDocuments(Filters.eq("uuid", user.getUniqueId().toString())) == 0) {
            getDatabase().getCollection("users").insertOne(document);
        } else {
            getDatabase().getCollection("users").updateOne(new Document("uuid", user.getUniqueId().toString()), new Document("$set", document));
        }
    }

}
