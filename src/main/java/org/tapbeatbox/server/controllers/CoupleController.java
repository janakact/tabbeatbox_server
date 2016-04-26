package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.config.AppConfig;
import org.tapbeatbox.server.resources.MatchResource;
import org.tapbeatbox.server.resources.PairResource;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * @author Adroitlogic
 */
@Path("/couple")
public class CoupleController {

    @POST
    @Path("/")
    @Produces("application/json")
    @Consumes("application/json")
    public Response match(MatchResource matchResource){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(AppConfig.REMOTE_HOST).path("rest").path("couple");
        Response response = webTarget.request()
                .header("Authorization", AppConfig.STUDENT_KEY)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(matchResource, MediaType.APPLICATION_JSON_TYPE));
        return response;
    }


    @GET
    @Path("/")
    @Produces("application/json")
    public PairResource[] matches(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(AppConfig.REMOTE_HOST).path("rest").path("couple");
        PairResource[] pairResources = webTarget.request()
                .header("Authorization", AppConfig.STUDENT_KEY)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(PairResource[].class);
        return pairResources;
    }

    @DELETE
    @Path("/{coupleID}")
    @Produces("application/json")
    public Response delete(@PathParam("coupleID") Integer coupleID){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(AppConfig.REMOTE_HOST).path("rest").path("couple").path(coupleID.toString());
        Response response = webTarget.request()
                .header("Authorization", AppConfig.STUDENT_KEY)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .delete();
        return response;
    }

}

