package domain;

import curriculatorapp.domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testit User oliolle.
 */
public class UserTest {

    User user;

    @Before
    public void setUpClass() {
        user = new User("TestName", "TestUserName");
    }

    /**
     * Tarkistetaan että salasanan tarkistus toimii oikein.
     */
    @Test
    public void testCheckPassword() {
        user.setPassword("TestPassword");
        assertTrue(user.checkPassword("TestPassword", user.getPassword()));
    }

    /**
     * Tarkistetaan että tietokantaan lisätään kryptattu salasana.
     */
    @Test
    public void testNewUserFromDataBase() {
        user.setPassword("TestPassword");
        String hashPassword = user.getPassword();
        User testUser = new User(1, "TestDBName", "TestDBUsername", hashPassword);
        assertEquals(hashPassword, testUser.getPassword());

    }

    /**
     * Testaa gettereitä ja settereitä.
     */
    @Test
    public void testNameGetterAndSetter() {
        String newName = "NewName";
        user.setName("NewName");
        assertEquals(newName, user.getName());

    }

    /**
     * Testaa gettereitä ja settereitä.
     */
    @Test
    public void testUsernameGetterAndSetter() {
        String newName = "NewUsername";
        user.setUsername("NewUsername");
        assertEquals(newName, user.getUsername());

    }

}
