package org.tapbeatbox.server.controllers;

import org.tapbeatbox.server.models.*;
import org.tapbeatbox.server.models.CustomMessageResource;

import javax.ws.rs.*;


/**
 * @author Janaka Chathuranga
 */
@Path("/user")
public class UserController {

    //    User Login
    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public Object login(LoginResource loginDetails){

        User user =  User.login(loginDetails);
        if(user==null) return new CustomMessageResource("Invalid User Details");
        return user;
    }

    //user signup
    @POST
    @Path("/signup")
    @Produces("application/json")
    @Consumes("application/json")
    public Object signUp(SignupResource signupResource){
        User.createUser(signupResource);
        return new CustomMessageResource("Account Is successfully created!");
    }
}

