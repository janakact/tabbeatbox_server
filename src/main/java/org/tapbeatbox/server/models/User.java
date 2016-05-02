package org.tapbeatbox.server.models;

import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.sun.research.ws.wadl.Doc;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.tapbeatbox.server.common.DbManager;
import org.tapbeatbox.server.common.PasswordManager;

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
        FindIterable<Document> iterable = db.getCollection("Users").find( new Document("username",loginResource.getUsername()));

        logger.debug("Loaded data from mongo db");
        Document doc = iterable.first();

        if(doc==null) return null;


        logger.debug("A valid user exist");
        User user = new User();
        user.setName((String)doc.get("name"));
        user.setUsername((String)doc.get("username"));
        user.setPasswordHash((String)doc.get("passwordHash"));

        //Validate with the enterd password
        if(PasswordManager.checkPassword(loginResource.getPassword(),user.getPasswordHash()))
            return user;
        return null;
    }

    public static void createUser(User user)
    {
        MongoDatabase db = DbManager.getInstance().getDb();

        Document doc = new Document("username",user.getUsername())
                .append("name",user.getName())
                .append("passwordHash", user.getPasswordHash());

        db.getCollection("Users").insertOne(doc);
    }
    public static void createUser(SignupResource userSignup) {
        User user = new User();
        user.setUsername(userSignup.getUsername());
        user.setPasswordHash(PasswordManager.hashPassword(userSignup.getPassword()));
        user.setName(userSignup.getName());

        User.createUser(user);
    }

    public static void removeUser(String username)
    {
        MongoDatabase db = DbManager.getInstance().getDb();
        db.getCollection("Users").deleteMany(new Document("username", username));
    }


}

