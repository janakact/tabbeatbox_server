/**
 * Created by Janaka on 2016-04-27.
 */
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.apache.log4j.or.ObjectRenderer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tapbeatbox.server.common.DbManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class Test_DBManager {

    static final Logger logger = Logger.getLogger(Test_DBManager.class);
    private DbManager dbManager;

    @Before
    public void setUp() {
        //Configure the logger
        BasicConfigurator.configure();
        logger.debug("Testing Starting");

        //Create a DBManager Instance
        dbManager = DbManager.getInstance();
        logger.debug("Instance Created");
    }

    @After
    public void cleanUp() {
        logger.debug("Testing Done!");
    }

    @Test
    public void testConnection() {
        //Check it is possible to get an instance. It is required to connect to the db to get an instance
        Object a = DbManager.getInstance();
        assertNotEquals(a,null);
    }

    @Test
    public void testDBExistence() {
        //Check it is possible to get an instance. It is required to connect to the db to get an instance
        Object a = DbManager.getInstance();
        assertTrue(a instanceof DbManager);
    }

    @Test
    public void testCollectionExistence() {
        //Check it is possible to get an instance. It is required to connect to the db to get an instance
        DbManager a = DbManager.getInstance();
        assertTrue( a.getDb() instanceof MongoDatabase);
        MongoDatabase db = a.getDb();
        Object col = db.getCollection("DataSets");
        assertTrue(col instanceof MongoCollection);
        col = db.getCollection("Users");
        assertTrue(col instanceof MongoCollection);
    }

}
