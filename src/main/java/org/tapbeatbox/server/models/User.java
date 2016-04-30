package org.tapbeatbox.server.models;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.tapbeatbox.server.common.DbManager;
import org.tapbeatbox.server.resources.LoginResource;

/**
 * Created by Janaka on 2016-04-30.
 */
public class User {
    private String username;
    private String passwordHash;
    private String name;


    static final Logger logger = Logger.getLogger(DbManager.class);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User login(LoginResource loginResource)
    {
        MongoDatabase db =  DbManager.getInstance().getDb();
        FindIterable<Document> iterable = db.getCollection("Users").find( new Document("username",loginResource.getUsername()).append("password",loginResource.getPassword()));

        logger.debug("Loaded data from mongo db");
        Document doc = iterable.first();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                logger.debug(document);
            }
        });
        if(doc==null) return null;

        logger.debug("A valid user exist");
        User user = new User();
        user.setName((String)doc.get("name"));
        user.setUsername((String)doc.get("username"));
        user.setPasswordHash((String)doc.get("password"));

        return user;
    }
}

