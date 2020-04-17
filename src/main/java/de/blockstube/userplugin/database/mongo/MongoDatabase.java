package de.blockstube.userplugin.database.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import de.blockstube.userplugin.database.Database;
import de.blockstube.userplugin.database.DatabaseConfiguration;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.Collections;

/**
 * @author schlingeldrops
 * This is a mongo server class, it's to manage the connection between the application and the server.
 */
public class MongoDatabase extends Database {

    private MongoClient client;

    public MongoDatabase(DatabaseConfiguration configuration) {
        super(configuration);
        final CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new UuidCodec(UuidRepresentation.STANDARD)),
                MongoClient.getDefaultCodecRegistry()
        );

        final MongoClientOptions clientOptions = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

        if (configuration.getUsername().isEmpty()) {
            client = new MongoClient(
                    new ServerAddress(configuration.getHost(), configuration.getPort()),
                    clientOptions
            );
        } else {
            client = new MongoClient(
                    new ServerAddress(configuration.getHost(), configuration.getPort()),
                    Collections.singletonList(
                            MongoCredential.createCredential(configuration.getUsername(), configuration.getDatabase(), configuration.getPassword().toCharArray())
                    ),
                    clientOptions
            );
        }
    }

    /**
     * Warning, the client can not be linked with the database,
     * please use the other methods {@link #getDatabase()} and {@link #getCollection(String)} when it goes.
     * @return The client.
     */
    public MongoClient getClient() {
        return client;
    }

    /**
     * @return The selected database.
     */
    public com.mongodb.client.MongoDatabase getDatabase() {
        return client.getDatabase(getConfiguration().getDatabase());
    }

    /**
     * @param name The collection name.
     * @return The collection.
     */
    public MongoCollection<Document> getCollection(String name) {
        return getDatabase().getCollection(name);
    }
}
