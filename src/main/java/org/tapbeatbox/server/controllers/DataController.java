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

        DataSet d = DataSet.getDataSet(id);
        //d.setSetId(id);
        return d;
    }
}

