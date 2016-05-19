/**
 * Created by Janaka on 2016-04-27.
 */
import org.apache.log4j.or.ObjectRenderer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tapbeatbox.server.common.DbManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

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

}
