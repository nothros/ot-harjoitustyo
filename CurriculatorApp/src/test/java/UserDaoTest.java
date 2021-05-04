/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.User;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ehorrosw
 */
public class UserDaoTest {

    public UserDaoTest() {
    }

    UserDao userDao;
    User user;
    String testName, testUserName, testPassword;
    String testDatabase;
    @Before
    public void setUp() throws FileNotFoundException, SQLException {
        testDatabase="testdb";
        testName = "testName";
        testUserName = "testUsername";
        testPassword = "testUserPassword";

        userDao = new UserDao(testDatabase+".db");
        userDao.createNewUserTable();
        user = new User(testName, testUserName);
        user.setPassword(testPassword);

    }

    @Test
    public void createUserAndFindsByUsername() throws SQLException {
        userDao.createUser(user.getName(), user.getUsername(), user.getPassword());
        User u = (User) userDao.findByUsername("testUsername");

        assertEquals("testName", u.getName());
        assertEquals("testUsername", u.getUsername());
    }
    
    @Test
    public void ncreateUserNotInDB() throws SQLException {
        userDao.createUser(user.getName(), user.getUsername(), user.getPassword());
        User u = (User) userDao.findByUsername("noUserName");

        assertEquals(null, u);
  
        
    }
    

    @After
    public void tearDown() throws SQLException {
        userDao.deleteDatabase(testDatabase);
    }
}
