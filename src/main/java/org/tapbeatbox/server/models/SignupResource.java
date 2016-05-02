package org.tapbeatbox.server.models;

/**
 * Created by Janaka on 2016-04-30.
 */
public class SignupResource {
    private String username;
    private String password;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public LoginResource(String username, String password)
//    {
//        this.username = username;
//        this.password = password;
//    }
}
