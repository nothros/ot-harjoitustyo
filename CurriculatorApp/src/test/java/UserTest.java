/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import curriculatorapp.domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ehorrosw
 */
public class UserTest {

    User user;

    @Before
    public void setUpClass() {
        user = new User("TestName", "TestUserName");
    }

    @Test
    public void testCheckPassword() {
        user.setPassword("TestPassword");
        assertTrue(user.checkPassword("TestPassword", user.getPassword()));
    }

    @Test
    public void testNewUserFromDataBase() {
        user.setPassword("TestPassword");
        String hashPassword = user.getPassword();
        User testUser = new User("TestDBName", "TestDBUsername", hashPassword);
        assertEquals(hashPassword, testUser.getPassword());

    }

    @Test
    public void testNameGetterAndSetter() {
        String newName = "NewName";
        user.setName("NewName");
        assertEquals(newName, user.getName());

    }

    @Test
    public void testUsernameGetterAndSetter() {
        String newName = "NewUsername";
        user.setUsername("NewUsername");
        assertEquals(newName, user.getUsername());

    }

}
