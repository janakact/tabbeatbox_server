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
        BasicConfigurator.configure();
        logger.debug("Testing Starting");
        dbManager = DbManager.getInstance();
        logger.debug("Instance Created");
    }

    @After
    public void cleanUp() {
        logger.debug("Testing Done!");
    }

    @Test
    public void testConnection() {
        Object a = DbManager.getInstance();
        assertNotEquals(a,null);
    }

    @Test
    public void testUsers()
    {
        dbManager.getUsers();
    }

}
