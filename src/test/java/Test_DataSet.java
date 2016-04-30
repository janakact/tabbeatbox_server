/**
 * Created by Janaka on 2016-04-30.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.tapbeatbox.server.common.PasswordManager;
import org.tapbeatbox.server.models.Data;
import org.tapbeatbox.server.models.DataSet;
import org.tapbeatbox.server.models.LoginResource;
import org.tapbeatbox.server.models.User;

import java.util.ArrayList;
import java.util.List;

public class Test_DataSet {
    static final Logger logger = Logger.getLogger(Test_DBManager.class);

    DataSet dataset;
    String userPassword;

    @Before
    public void setUp() {
        BasicConfigurator.configure();
        logger.debug("Testing Starting - User");


        logger.info("Creating a new user");
        //Create a dummy user
        dataset = new DataSet();
        dataset.setSetId(923); // Unique
        dataset.setDeviceId(111);
        dataset.setDeviceModel("dummyuser");
        dataset.setSlotId(32);

        List<Data> dataList = new ArrayList<>();
        dataList.add(Data.getObject(12,223.3));
        dataList.add(Data.getObject(112,2.3));
        dataList.add(Data.getObject(92,-5.3));
        dataset.setDataList(dataList);
        DataSet.saveDataSet(dataset);

    }


    @After
    public void cleanUp() {
        logger.info("Removing the created user");
//        DataSet.removeDataSet(dataset.getSetId());
        logger.debug("Testing Done! - User");
    }


    @Test
    public void testForInsertedDataSet() {
        logger.info("Trying to login with the new user");
        //Try login with the created user
        DataSet ds = DataSet.getDataSet(dataset.getSetId());
        assertNotEquals(ds,null);
        assertEquals(ds.getDeviceId(),dataset.getDeviceId());
        assertEquals(ds.getDeviceModel(),dataset.getDeviceModel());
        assertEquals(ds.getSetId(),dataset.getSetId());
        assertEquals(ds.getSlotId(),dataset.getSlotId());
    }


}
