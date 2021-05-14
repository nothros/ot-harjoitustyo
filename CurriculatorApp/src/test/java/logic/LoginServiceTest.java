package logic;

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.dao.UserDao;
import curriculatorapp.logic.LoginService;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testit LoginService luokalle.
 */
public class LoginServiceTest {

    private UserDao testuserdao;
    private CurriculumDao testcurriculumdao;
    private CoursesDao testcoursesdao;
    LoginService loginService;

    /**
     * Asettaa tarvittavan tietokannat ja loginServicen.
     */
    @Before
    public void setUp() throws SQLException {
        testuserdao = new UserDao("testuserDB.db");
        testcurriculumdao = new CurriculumDao("testcurriculumDB.db");
        testcoursesdao = new CoursesDao("testCourse.db");
        loginService = new LoginService(testuserdao, testcurriculumdao, testcoursesdao);
        loginService.createNewTablesIfNotExists();

    }

    /**
     * Käyttäjätunnusta ei löydy kirjautuessa.
     */
    @Test
    public void loginCanNotFindUser() throws SQLException {
        boolean notExists = loginService.checkUsernameAndPassword("usernameNotExists", "noPassWord");
        assertEquals(false, notExists);
    }

    /**
     * Käyttäjätunnus löytyy kirjautuessa.
     */
    @Test
    public void loginFindUser() throws SQLException {
        boolean isTrue = loginService.createNewUser("testFindname", "testFindUser", "testFindPassword");
        boolean successLogin = loginService.checkUsernameAndPassword("testFindUser", "testFindPassword");
        assertEquals(true, successLogin);
    }

    /**
     * Käyttäjän lisääminen onnistuu.
     */
    @Test
    public void createNewUserReturnsTrue() throws SQLException {
        boolean mustBeTrue = loginService.createNewUser("Testname", "Testusername", "Testpassword");
        assertEquals(true, mustBeTrue);
    }

    /**
     * Käyttäjän lisääminen ei onnistu, koska on jo olemassa.
     */
    @Test
    public void createNewUserWhenUsernameExists() throws SQLException {
        boolean isTrue = loginService.createNewUser("OtherTestName", "OtherUserName", "OtherTestpassword");
        boolean isFalse = loginService.createNewUser("Testing", "OtherUserName", "OtherTestpassword");
        assertEquals(false, isFalse);
    }

    /**
     * Poistaa tietokannat kokonaan.
     */
    @After
    public void tearDown() throws SQLException {
        testuserdao.deleteDatabase("testuserDB");
        testcurriculumdao.deleteDatabase("testcurriculumDB");
        testcoursesdao.deleteDatabase("testCourse");
    }

}
