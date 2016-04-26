package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.config.AppConfig;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class will mediate the student authentication process
 * @author Chathura Widanage
 */
@Path("/auth")
public class AuthController {
    @GET
    @Path("/")
    @Produces("application/json")
    public Response getStudent(@HeaderParam("Authorization") String authKey) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(AppConfig.REMOTE_HOST).path("rest").path("student").path("auth");
        return webTarget.request().
                header("Authorization", AppConfig.STUDENT_KEY).
                accept(MediaType.APPLICATION_JSON_TYPE).
                get();
    }
}