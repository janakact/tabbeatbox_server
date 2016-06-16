package org.tapbeatbox.server.common;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Janaka on 2016-04-30.
 */
public class PasswordManager {
    private static int workload = 12;

    /**
     * This method is to be used to generate a string representing an account password
    */
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return(hashed_password);
    }

    /**
     * This method is to be used to verify a computed hash from a plaintext (e.g. during a login
    */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }
}
