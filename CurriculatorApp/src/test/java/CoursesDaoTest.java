/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class CoursesDaoTest {

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

    public CoursesDaoTest() {
    }

    @Before
    public void setUp() throws SQLException {
        testDatabase = "curriculumTestDB";
        coursesDao = new CoursesDao(testDatabase + ".db");
        setUpUserTable();
        setUpCurriculumTable();
        coursesDao.createNewTable();
    }

    public void setUpUserTable() throws SQLException {
        testName = "testName";
        testUserName = "testUsername";
        testPassword = "testUserPassword";

        userDao = new UserDao(testDatabase + ".db");
        userDao.createNewUserTable();
        user = new User(testName, testUserName);
        user.setPassword(testPassword);
        userDao.createUser(user.getName(), user.getUsername(), user.getPassword());

    }

    public void setUpCurriculumTable() throws SQLException {
        user = userDao.findByUsername(testUserName);

        curriculumDao = new CurriculumDao(testDatabase + ".db");
        curriculumDao.createNewTable();
        curriculumDao.createCurriculum(user, "Test", 200, "Choice");

    }

    @Test
    public void checkIfReturnAllCoursesFromUser() throws SQLException {
        curriculumtest = curriculumDao.findCurriculum(user);
        coursesDao.createCourse(curriculumtest, "Course1", 10);
        coursesDao.createCourse(curriculumtest, "Course2", 10);
        coursesDao.createCourse(curriculumtest, "Course3", 10);
        List<Course> courses = new ArrayList<>();
        courses.add(coursesDao.findOneCourse(curriculumtest, "Course1"));
        courses.add(coursesDao.findOneCourse(curriculumtest, "Course2"));
        courses.add(coursesDao.findOneCourse(curriculumtest, "Course3"));
        List<Course> testCourses = coursesDao.findAllCourses(curriculumtest);
        assertEquals(courses, testCourses);
    }

    @Test
    public void checkIfCourseUpDate() throws SQLException {
        curriculumtest = curriculumDao.findCurriculum(user);
        coursesDao.createCourse(curriculumtest, "Course1", 10);
        Course check = coursesDao.findOneCourse(curriculumtest, "Course1");
        coursesDao.updateCourse(check, "5");
        Course check2 = coursesDao.findOneCourse(curriculumtest, "Course1");

        assertNotEquals(check, check2);
    }

    @After
    public void tearDown() throws SQLException {
        coursesDao.deleteDatabase(testDatabase);
    }
}
