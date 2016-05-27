package org.tapbeatbox.server.models;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.tapbeatbox.server.common.DbManager;

import java.util.*;


/**
 * Created by Janaka on 2016-04-30.
 */
public class DataSet {
    private List<Data> dataList;    // List of readings
    private String setId; //Unique for the set
    private int slotId; //Specify the slot
    private int deviceId;
    private String deviceModel;
    private String id;

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    //Save into the database
    public static String saveDataSet(DataSet dataSet)
    {
        MongoDatabase db = DbManager.getInstance().getDb();
        List<BasicDBObject> saveList = new ArrayList<>();
        List<Data> dataList = dataSet.getDataList();
        for (int i=0; i<dataList.size();i++) {
            saveList.add(new BasicDBObject("time",dataList.get(i).getTime() ).append("x",dataList.get(i).getX()).append("y",dataList.get(i).getY()).append("z",dataList.get(i).getZ()) );
        }

        Document doc = new Document("slotId", dataSet.getSlotId())
               // .append("setId",dataSet.getSetId())
                .append("deviceId",dataSet.getDeviceId())
                .append("deviceModel",dataSet.getDeviceModel())
                .append("dataList",saveList);

        db.getCollection("DataSets").insertOne(doc);
        return ((ObjectId) doc.get("_id")).toString();
    }

    //Remove from the database
    public static void removeDataSet(String setId)
    {
        MongoDatabase db = DbManager.getInstance().getDb();
        db.getCollection("DataSets").deleteMany(new Document("_id", setId));
    }

    /**
     * This method is to load a single dataset from the db
     * @param setId The id of the DataSet(Unique)
     * @return DataSet, if it can be found
     */
    public static DataSet getDataSet(String setId)
    {

        MongoDatabase db = DbManager.getInstance().getDb();
        FindIterable<Document> docs = db.getCollection("DataSets").find(new Document("_id",new ObjectId(setId)));

        Document doc = docs.first();

//        dataSet.setSlotId((int)doc.get("slotId"));
        return convertDocToDataSet(doc);
    }

    /**
     * Get all the data sets from the databaseS
     * @return DataSet, if it can be found
     */
    public static List<DataSet> getAll()
    {
        final List<DataSet> list = new ArrayList<>();
        MongoDatabase db = DbManager.getInstance().getDb();
        FindIterable<Document> docs = db.getCollection("DataSets").find();

        docs.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                list.add(convertDocToDataSet(document));
            }
        });

        return list;
    }

    /**
     * This method is to convert mongo document object to a DataSet
     * @param doc the document to be converted
     * @return Generated DataSet
     */
    private static DataSet convertDocToDataSet(final Document doc)
    {
        DataSet dataSet = new DataSet();
        dataSet.setSlotId((int)doc.get("slotId"));


        dataSet.setSetId(((ObjectId) doc.get("_id")).toString());
        dataSet.setDeviceModel((String )doc.get("deviceModel"));
        dataSet.setDeviceId((int )doc.get("deviceId"));
        dataSet.setId(doc.get("_id").toString());

        List<Data> iterable = (List<Data>) doc.get("dataList");
        dataSet.setDataList(iterable);

//        dataSet.setSlotId((int)doc.get("slotId"));
        return dataSet;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
