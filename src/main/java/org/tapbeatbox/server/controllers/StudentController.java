package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.config.AppConfig;
import org.tapbeatbox.server.resources.StudentResource;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author Adroitlogic
 */
@Path("/student")
public class StudentController {
    @GET
    @Path("/")
    @Produces("application/json")
    public StudentResource[] list(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(AppConfig.REMOTE_HOST).path("rest").path("student");
        final StudentResource[] studentResources = webTarget.request()
                .header("Authorization", AppConfig.STUDENT_KEY)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(StudentResource[].class);
        return studentResources;
    }
}

