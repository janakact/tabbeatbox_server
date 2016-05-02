package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.models.*;
import org.tapbeatbox.server.resources.CustomMessageResource;

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
    public Object login(LoginResource loginDetails){

        User user =  User.login(loginDetails);
        if(user==null) return new CustomMessageResource("Invalid User Details");
        return user;
    }

    @POST
    @Path("/signup")
    @Produces("application/json")
    @Consumes("application/json")
    public Object signUp(SignupResource signupResource){
        User.createUser(signupResource);
//        User user = User.login()
        return new CustomMessageResource("Account Is successfully created!");
    }
}

