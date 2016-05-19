package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.models.*;
import org.tapbeatbox.server.models.CustomMessageResource;

import javax.ws.rs.*;

import java.util.List;


/**
 * @author Janaka Chathuranga
 */
@Path("/data")
public class DataController {

    private Integer id;

//    Return all the datasets
    @GET
    @Path("/all")
    @Produces("application/json")
    public Object getAll(){

        List<DataSet> d = DataSet.getAll();
        return d;
    }

//    return a specific dataset
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Object getOne(@PathParam("id") Integer id){
        this.id = id;

        DataSet d = DataSet.getDataSet(id);
        return d;
    }

//    Delete a specific dataset
    @POST
    @Path("/delete/{id}")
    @Produces("application/json")
    public Object login(@PathParam("id") Integer id){
        DataSet.removeDataSet(id);
        return new CustomMessageResource("Data Set is Successfully Deleted");
    }

    //    Add data
    @POST
    @Path("/add")
    @Produces("application/json")
    @Consumes("application/json")
    public Object add(DataSet dataSet){

        //return new CustomMessageResource( dataSet.getSlotId()+"");

        DataSet.saveDataSet(dataSet);
        return new CustomMessageResource("Data Set is Successfully Saved");
    }
}

