import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class TestCases {

    @Test
    public void testValidLogin() throws Exception {
        LoginApp loginApp = new LoginApp();

        Method authenticateMethod = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        authenticateMethod.setAccessible(true);

        String result = (String) authenticateMethod.invoke(loginApp, "johndoe@example.com", "password123");
        Assert.assertEquals("John Doe", result); // Assumes the database has this record
    }

    @Test
    public void testInvalidLogin() throws Exception {
        LoginApp loginApp = new LoginApp();

        Method authenticateMethod = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        authenticateMethod.setAccessible(true);

        String result = (String) authenticateMethod.invoke(loginApp, "invaliduser@example.com", "wrongpassword");
        Assert.assertNull(result);
    }

    @Test
    public void testEmptyEmailAndPassword() throws Exception {
        LoginApp loginApp = new LoginApp();

        Method authenticateMethod = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        authenticateMethod.setAccessible(true);

        String result = (String) authenticateMethod.invoke(loginApp, "", "");
        Assert.assertNull(result);
    }

    @Test
    public void testEmptyPassword() throws Exception {
        LoginApp loginApp = new LoginApp();

        Method authenticateMethod = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        authenticateMethod.setAccessible(true);

        String result = (String) authenticateMethod.invoke(loginApp, "user@example.com", "");
        Assert.assertNull(result);
    }

    @Test
    public void testSQLInjectionAttempt() throws Exception {
        LoginApp loginApp = new LoginApp();

        Method authenticateMethod = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        authenticateMethod.setAccessible(true);

        String result = (String) authenticateMethod.invoke(loginApp, "user@example.com", "' OR '1'='1");
        Assert.assertNull(result); // Database should not return a user
    }
}
