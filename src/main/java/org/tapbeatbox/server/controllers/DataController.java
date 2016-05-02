package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.models.*;
import org.tapbeatbox.server.resources.CustomMessageResource;

import javax.ws.rs.*;

import java.util.List;

import static java.util.Arrays.asList;


/**
 * @author Adroitlogic
 */
@Path("/data")
public class DataController {

    private Integer id;

    @GET
    @Path("/all")
    @Produces("application/json")
    public Object getAll(){

        List<DataSet> d = DataSet.getAll();
        return d;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Object getOne(@PathParam("id") Integer id){
        this.id = id;

        DataSet d = DataSet.getDataSet(id);
        //d.setSetId(id);
        return d;
    }

    @POST
    @Path("/delete/{id}")
    @Produces("application/json")
    public Object login(@PathParam("id") Integer id){
        DataSet.removeDataSet(id);
        return new CustomMessageResource("Data Set is Successfully Deleted");
    }
}

