/**
 * Created by Janaka on 2016-04-30.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.runners.MethodSorters;
import org.tapbeatbox.server.common.PasswordManager;
import org.tapbeatbox.server.models.LoginResource;
import org.tapbeatbox.server.models.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_User {
    static final Logger logger = Logger.getLogger(Test_DBManager.class);

    User user,user2;
    String userPassword;

    @Before
    public void setUp() {
        //Configure the logger
        BasicConfigurator.configure();
        logger.debug("Testing Starting - User");

        //write the log
        logger.info("Creating a new user");

        //Create a dummy user
        user = new User();
        user.setName("Dummy User");
        user.setUsername("dummyuser");
        userPassword = "dummypassword";
        user.setPasswordHash(PasswordManager.hashPassword(userPassword));
        User.createUser(user);


        user2 = new User();
        user2.setName("Dummy User 2");
        user2.setUsername("dummyuser2");
        user2.setPasswordHash(PasswordManager.hashPassword("password"));


    }


    @After
    public void cleanUp()
    {
        //Remove the created user
        logger.info("Removing the created user");
        User.removeUser(user.getUsername());
        logger.debug("Testing Done! - User");
    }


    @Test
    public void login() {
        logger.info("Trying to login with the new user");
        //Try login with the created user
        LoginResource loginResource = new LoginResource();
        loginResource.setPassword(userPassword);
        loginResource.setUsername(user.getUsername());

        //Check the login is successful
        User loadedUser = User.login(loginResource);
        assertNotEquals(loadedUser,null);
        assertEquals(loadedUser.getName(), user.getName());
        assertEquals(loadedUser.getUsername(), user.getUsername());
        assertEquals(loadedUser.getPasswordHash(), user.getPasswordHash());
    }

    @Test
    public void createUser() {

        //write the log
        logger.info("Creating a new user");
        //Create a dummy user
        User.createUser(user2);
    }

    @Test
    public void removeUser()
    {
        //Remove the created user
        logger.info("Removing the created user");
        User.removeUser(user2.getUsername());
        logger.debug("Testing Done! - User");
    }

}
