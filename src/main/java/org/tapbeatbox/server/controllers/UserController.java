package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.models.Data;
import org.tapbeatbox.server.models.DataSet;
import org.tapbeatbox.server.models.User;
import org.tapbeatbox.server.resources.CustomMessageResource;
import org.tapbeatbox.server.models.LoginResource;

import javax.ws.rs.*;

import static java.util.Arrays.asList;


/**
 * @author Adroitlogic
 */
@Path("/user")
public class UserController {

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public Object match(LoginResource loginDetails){

//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target(AppConfig.REMOTE_HOST).path("rest").path("couple");
//        Response response = webTarget.request()
//                .header("Authorization", AppConfig.STUDENT_KEY)
//                .accept(MediaType.APPLICATION_JSON_TYPE)
//                .post(Entity.entity(matchResource, MediaType.APPLICATION_JSON_TYPE));

        User user =  User.login(loginDetails);
        if(user==null) return new CustomMessageResource("Invalid User Details");

        DataSet d = DataSet.getDataSet(921);
        Data d1 = new Data();
        Data d2 = new Data();

        d1.setTime(12);
        d1.setValue(120.4);
        d2.setTime(12);
        d2.setValue(120.4);


        d.setDataList(asList(d1,d2));
        return d;
    }
}

