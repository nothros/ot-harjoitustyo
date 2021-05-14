package dao;

import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa CurriculumDao-luokkaa ja tietokannan toimintaa.
 *
 *
 */
public class CurriculumDaoTest {

    Curriculum curriculum;
    UserDao userDao;
    CurriculumDao curriculumDao;
    User user;
    String testName, testUserName, testPassword;
    String testDatabase;

    /**
     * Metodi tarvittavien tietokantojen rakentamiseen.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.sql.SQLException
     */
    @Before
    public void setUp() throws FileNotFoundException, SQLException {
        testDatabase = "curriculumTestDB";
        setUpUserTable();
        curriculumDao = new CurriculumDao(testDatabase + ".db");
        curriculumDao.createNewTable();

    }

    /**
     * Apumetodi käyttäjän lisäämiseen ja tietokannan rakentamiseen.
     *
     * @throws java.sql.SQLException
     */
    public void setUpUserTable() throws SQLException {
        testName = "testName";
        testUserName = "testUsername";
        testPassword = "testUserPassword";

        userDao = new UserDao(testDatabase + ".db");
        userDao.createNewUserTable();
        user = new User(testName, testUserName);
        user.setPassword(testPassword);

    }

    /**
     * Tarkistaa, että tietokantaa ei ole olamassa.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testIfCurrilumDoesNotExists() throws SQLException {
        user = new User("NoName", "NoUserName");
        user.setPassword("NoPass");
        assertEquals(null, curriculumDao.findCurriculum(user));

    }

    /**
     * Tarkistaa tietokanta lisää curriculumin tietokantaan
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testIfCurrilumDoesExists() throws SQLException {
        curriculum = new Curriculum(1, "name", "choice", 3, user);
        curriculumDao.createCurriculum(user, "name", 3, "choice");
        assertEquals(curriculum, curriculumDao.findCurriculum(user));

    }

    /**
     * Poistaa tietokantatiedoston.
     *
     * @throws java.sql.SQLException
     */
    @After
    public void tearDown() throws SQLException {
        curriculumDao.deleteDatabase(testDatabase);
    }

}
