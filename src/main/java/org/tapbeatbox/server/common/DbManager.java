package org.tapbeatbox.server.common;

/**
 * Created by Janaka on 2016-04-27.
 */

import com.mongodb.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;

import org.bson.Document;
import org.tapbeatbox.server.models.LoginResource;

public class DbManager {

    static final Logger logger = Logger.getLogger(DbManager.class);
    private MongoDatabase db;

    //Instance for singleton
    private static DbManager dbManager;

    //Singleton create instance
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

            MongoClientURI uri = new MongoClientURI("mongodb://dbuser:n1ckname@ds021751.mlab.com:21751/tapbeatbox_data");
            MongoClient mongoClient = new MongoClient(uri );

            // To connect to mongodb server
           // MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // Now connect to your databases
            db = mongoClient.getDatabase( "tapbeatbox_data" );
            logger.debug("Connect to database successfully");

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return ;
        }
    }

//    Return the database for use in other methods
    public MongoDatabase getDb(){
        return db;
    }
}
