/**
 * Created by Janaka on 2016-04-30.
 */

import org.apache.logging.log4j.core.util.Assert;
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
    String docId;

    @Before
    public void setUp() {
        //Configure the logger
        BasicConfigurator.configure();
        logger.debug("Testing Starting - DataSet");


        logger.info("Creating a new Dataset");
        //Create a dummy user
        dataset = new DataSet();
        dataset.setDeviceId(111);
        dataset.setDeviceModel("datasetNow");
        dataset.setSlotId(32);

        //Create Data
        List<Data> dataList = new ArrayList<>();
        dataList.add(Data.getObject(1,1.2,3.4,5.0));
        dataList.add(Data.getObject(2,1.2,3.4,1.0));
        dataList.add(Data.getObject(3,4.2,3.4,3.0));
        dataset.setDataList(dataList);
        docId = DataSet.saveDataSet(dataset);

        dataset.setSetId(docId);
        logger.info("Saved the Dataset with ID:"+docId);

    }


    @After
    public void cleanUp() {
        logger.info("Removing the created dataset");
        DataSet.removeDataSet(dataset.getSetId());
        assertEquals(DataSet.getDataSet(dataset.getSetId()),null);
        logger.debug("Testing Done! - DataSet");
    }


    @Test
    public void testForDataExistence() {
        logger.info("Trying load the created dataset");
        //Try load the dataset back and check for validity
        DataSet ds = DataSet.getDataSet(docId);
        assertNotEquals(ds,null);
    }


    @Test
    public void testForDataValidity() {
        //Try load the dataset back and check for validity
        DataSet ds = DataSet.getDataSet(docId);
        assertEquals(ds.getDeviceId(),dataset.getDeviceId());
        assertEquals(ds.getDeviceModel(),dataset.getDeviceModel());
        assertEquals(ds.getSetId(),dataset.getSetId());
        assertEquals(ds.getSlotId(),dataset.getSlotId());
        logger.info("Created the Data sate at time: " +ds.getTime());
    }



}
