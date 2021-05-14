package logic;

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import curriculatorapp.logic.AppService;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testit AppService luokalle.
 */
public class AppServiceTest {

    Curriculum curriculum;
    Curriculum curriculumtest;
    UserDao userDao;
    CurriculumDao curriculumDao;
    CoursesDao coursesDao;
    User user;
    User user2;
    String testName, testUserName, testPassword;
    String testDatabase;
    Course course;
    AppService appService;

    /**
     * Asettaa tarvittavan tietokannat ja AppServicen.
     */
    @Before
    public void setUp() throws SQLException {
        testDatabase = "courseTestDB";
        coursesDao = new CoursesDao(testDatabase + ".db");
        setUpUserTable();
        setUpCurriculumTable();
        coursesDao.createNewTable();
        appService = new AppService(user, curriculumDao, coursesDao);
        appService.createCurriculum("Name", 200, "Choice");
    }

    /**
     * Apuluokka User-taulun luomiseen.
     */
    public void setUpUserTable() throws SQLException {
        testName = "testName";
        testUserName = "testUsername";
        testPassword = "testUserPassword";

        userDao = new UserDao(testDatabase + ".db");
        userDao.createNewUserTable();
        user = new User(testName, testUserName);
        user.setPassword(testPassword);
        userDao.createUser(user.getName(), user.getUsername(), user.getPassword());
        user = userDao.findByUsername(testUserName);

    }

    /**
     * Apuluokka Curriculum-taulun luomiseen.
     *
     * @throws java.sql.SQLException
     */
    public void setUpCurriculumTable() throws SQLException {
        curriculumDao = new CurriculumDao(testDatabase + ".db");
        curriculumDao.createNewTable();

    }

    /**
     * Testi, että suoritettu prosenttimäärä on nolla.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void calculatePercentZero() throws SQLException {
        double DELTA = 1e-15;
        curriculumtest = appService.findCurriculum();
        double zero = appService.getProgressPercent();

        assertEquals(0.0, zero, DELTA);
    }

    /**
     * Testi joka testaa, että kursseja on oikea määrä suoritettuna.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void calculateLeftCoursesReturnRightAmount() throws SQLException {
        curriculumtest = appService.findCurriculum();
        appService.createCourse("Course1", 5);
        Course donecourse = coursesDao.findOneCourse(curriculumtest, "Course1");
        appService.markDone(donecourse, "5");
        String left = appService.calculateLeftCourses();

        assertEquals("195", left);

    }

    /**
     * Testi joka testaa, että kursseja palautetaan nolla jos suoritukset menee
     * yli.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void calculateLeftCoursesReturnsZero() throws SQLException {
        curriculumtest = appService.findCurriculum();
        appService.createCourse("Course2", 250);
        Course donecourse = coursesDao.findOneCourse(curriculumtest, "Course2");
        appService.markDone(donecourse, "5");
        String left = appService.calculateLeftCourses();

        assertEquals("0", left);

    }

    /**
     * Testi keskiarvon laskemiselle.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void calculateAverage() throws SQLException {
        curriculumtest = appService.findCurriculum();
        appService.createCourse("Course3", 4);
        Course donecourse = coursesDao.findOneCourse(curriculumtest, "Course3");
        appService.markDone(donecourse, "5");
        String average = appService.calculateAverageGrade();

        assertEquals("5.00", average);

    }

    /**
     * Testi, että suoritettu prosenttimäärä on 1.0.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void calculatePercent100() throws SQLException {
        double DELTA = 1e-15;
        curriculumtest = appService.findCurriculum();
        appService.createCourse("Course3", 200);
        Course donecourse = coursesDao.findOneCourse(curriculumtest, "Course3");
        appService.markDone(donecourse, "5");
        double one = appService.getProgressPercent();

        assertEquals(1.0, one, DELTA);
    }

    /**
     * Poistaa tietokannat kokonaan.
     *
     * @throws java.sql.SQLException
     */
    @After
    public void tearDown() throws SQLException {
        userDao.deleteDatabase(testDatabase);

        curriculumDao.deleteDatabase(testDatabase);
        coursesDao.deleteDatabase(testDatabase);
    }

}
