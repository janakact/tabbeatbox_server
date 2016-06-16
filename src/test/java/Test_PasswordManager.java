

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.tapbeatbox.server.common.DbManager;
import org.tapbeatbox.server.common.PasswordManager;
import org.w3c.dom.Document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test_PasswordManager {

    static final Logger logger = Logger.getLogger(Test_DBManager.class);
    private DbManager dbManager;

    @Test
    public  void testPasswordMapping()
    {
        String pass = "mypassword";
        String hash = PasswordManager.hashPassword(pass);
        assertTrue(PasswordManager.checkPassword(pass,hash));
        assertFalse(PasswordManager.checkPassword("SomethingElse",hash));
    }



}
