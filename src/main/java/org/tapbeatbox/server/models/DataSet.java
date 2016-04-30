package org.tapbeatbox.server.models;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.sun.research.ws.wadl.Doc;
import org.bson.Document;
import org.tapbeatbox.server.common.DbManager;

import java.util.*;


/**
 * Created by Janaka on 2016-04-30.
 */
public class DataSet {
    private List<Data> dataList;    // List of readings
    private int setId; //Unique for the set
    private int slotId; //Specify the slot
    private int deviceId;
    private String deviceModel;

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

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    //Save into the database
    public static void saveDataSet(DataSet dataSet)
    {
        MongoDatabase db = DbManager.getInstance().getDb();
        List<BasicDBObject> saveList = new ArrayList<>();
        List<Data> dataList = dataSet.getDataList();
        for (int i=0; i<dataList.size();i++) {
            saveList.add(new BasicDBObject("time",dataList.get(i).getTime() ).append("value",dataList.get(i).getValue()) );
        }

        Document doc = new Document("slotId", dataSet.getSlotId())
                .append("setId",dataSet.getSetId())
                .append("deviceId",dataSet.getDeviceId())
                .append("deviceModel",dataSet.getDeviceModel())
                .append("dataList",saveList);

        db.getCollection("DataSets").insertOne(doc);
    }

    //Remove from the database
    public static void removeDataSet(int setId)
    {
        MongoDatabase db = DbManager.getInstance().getDb();
        db.getCollection("DataSets").deleteMany(new Document("setId", setId));
    }

    public static DataSet getDataSet(int setId)
    {

        MongoDatabase db = DbManager.getInstance().getDb();
        FindIterable<Document> docs = db.getCollection("DataSets").find(new Document("setId",setId));

        Document doc = docs.first();
        DataSet dataSet = new DataSet();
        dataSet.setSlotId((int)doc.get("slotId"));
        dataSet.setSetId((int)doc.get("setId"));
        dataSet.setDeviceModel((String )doc.get("deviceModel"));
        dataSet.setDeviceId((int )doc.get("deviceId"));

//        dataSet.setSlotId((int)doc.get("slotId"));
        return dataSet;
    }

    private static List<Data> convertToData(List<Integer> times, List<Double> values)
    {
        List<Data> list = new ArrayList<Data>();
        for (int i=0; i<times.size(); i++)
        {
            Data d = new Data();
            d.setTime(times.get(i));
            d.setValue(values.get(i));
        }
        return list;
    }

}