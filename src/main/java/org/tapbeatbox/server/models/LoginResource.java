package org.tapbeatbox.server.models;

/**
 * Created by Janaka on 2016-04-30.
 */
public class LoginResource {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString()
    {
        return username;

    }

//    public LoginResource(String username, String password)
//    {
//        this.username = username;
//        this.password = password;
//    }
}
