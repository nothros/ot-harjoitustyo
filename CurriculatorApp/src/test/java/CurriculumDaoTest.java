/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.User;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ehorrosw
 */
public class CurriculumDaoTest {

    UserDao userDao;
    CurriculumDao curriculumDao;
    User user;
    String testName, testUserName, testPassword;
    String testDatabase;

    public CurriculumDaoTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, SQLException {
        testDatabase = "curriculumTestDB";
        setUpUserTable();
        curriculumDao = new CurriculumDao(testDatabase + ".db");
        curriculumDao.createNewTable();

    }
    public void setUpUserTable() throws SQLException {
        testName = "testName";
        testUserName = "testUsername";
        testPassword = "testUserPassword";

        userDao = new UserDao(testDatabase + ".db");
        userDao.createNewUserTable();
        user = new User(testName, testUserName);
        user.setPassword(testPassword);

    }

    @Test
    public void testIfCurrilumDoesNotExists() throws SQLException {
      //  assertEquals(false, curriculumDao.findCurriculum("UserNotExists"));

    }

    

    @After
    public void tearDown() throws SQLException {
        curriculumDao.deleteDatabase(testDatabase);
    }

}
