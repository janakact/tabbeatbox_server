package org.tapbeatbox.server.common;

/**
 * Created by Janaka on 2016-04-27.
 */

import com.mongodb.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.jar.Pack200;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.Document;

public class DbManager {

    static final Logger logger = Logger.getLogger(DbManager.class);
    private MongoDatabase db;

    private static DbManager dbManager;

    public static DbManager getInstance()
    {
        if(dbManager==null) dbManager = new DbManager();
        return dbManager;
    }

    private DbManager()
    {
        if(db!=null) return;
        logger.debug("Trying to Connect");
        try{

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // Now connect to your databases
            db = mongoClient.getDatabase( "test" );
            logger.debug( "Count is "+db.getCollection("DataSets").count());
            logger.debug("Connect to database successfully");

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return ;
        }
    }


    public int getUsers()
    {
        FindIterable<Document> iterable = db.getCollection("Users").find( new Document("username","janakact"));
        iterable.forEach(new Block<Document>() {
        @Override
        public void apply(final Document document) {
                logger.debug(document);
                }
                });

        return 0;
    }
}


//iterable.forEach(new Block<Document>() {
//@Override
//public void apply(final Document document) {
//        System.out.println(document);
//        }
//        });

//FindIterable<Document> iterable = db.getCollection("restaurants").find();
//
//    FindIterable<Document> iterable = db.getCollection("restaurants").find(
//            new Document("borough", "Manhattan"));