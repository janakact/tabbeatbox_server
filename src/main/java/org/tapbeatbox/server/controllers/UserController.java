package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.config.AppConfig;
import org.tapbeatbox.server.models.User;
import org.tapbeatbox.server.resources.LoginResource;
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
@Path("/user")
public class UserController {

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public String match(LoginResource loginDetails){

//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target(AppConfig.REMOTE_HOST).path("rest").path("couple");
//        Response response = webTarget.request()
//                .header("Authorization", AppConfig.STUDENT_KEY)
//                .accept(MediaType.APPLICATION_JSON_TYPE)
//                .post(Entity.entity(matchResource, MediaType.APPLICATION_JSON_TYPE));

        User user = User.login(loginDetails);

        return user.getName();
    }
}

