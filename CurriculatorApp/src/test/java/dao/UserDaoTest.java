package dao;

import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.User;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Kurssi UserDaon testaamiseen.
 */
public class UserDaoTest {

    UserDao userDao;
    User user;
    String testName, testUserName, testPassword;
    String testDatabase;

    /**
     * Lisää tarvittavat tietokannat ja käyttäjän.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.sql.SQLException
     */
    @Before
    public void setUp() throws FileNotFoundException, SQLException {
        testDatabase = "testdb";
        testName = "testName";
        testUserName = "testUsername";
        testPassword = "testUserPassword";

        userDao = new UserDao(testDatabase + ".db");
        userDao.createNewUserTable();
        user = new User(testName, testUserName);
        user.setPassword(testPassword);

    }

    /**
     * Tarkistetaan että käyttäjä löytyy tietokannasta.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void createUserAndFindsByUsername() throws SQLException {
        userDao.createUser(user.getName(), user.getUsername(), user.getPassword());
        User u = (User) userDao.findByUsername("testUsername");

        assertEquals("testUsername", u.getUsername());
    }

    /**
     * Tarkistetaan että käyttäjää ei ole tietokannassa.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void createUserNotInDB() throws SQLException {
        userDao.createUser(user.getName(), user.getUsername(), user.getPassword());
        User u = (User) userDao.findByUsername("noUserName");

        assertEquals(null, u);

    }

    /**
     * Poistaa tietokantatiedoston.
     *
     * @throws java.sql.SQLException
     */
    @After
    public void tearDown() throws SQLException {
        userDao.deleteDatabase(testDatabase);
    }
}
