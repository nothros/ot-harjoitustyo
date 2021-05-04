/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.User;
import curriculatorapp.logic.LoginService;
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
public class LoginServiceTest {
    private UserDao testuserdao;
    private CurriculumDao testcurriculumdao;
    private CoursesDao testcoursesdao;
    private User testloggedUser;
    LoginService loginService;
    
    public LoginServiceTest() {
    }
    
    @Before
    public void setUp() throws SQLException {
        testuserdao=new UserDao("testuserDB");
        testcurriculumdao=new CurriculumDao("testcurriculumDB");
        testcoursesdao=new CoursesDao();
        loginService=new LoginService(testuserdao,testcurriculumdao, testcoursesdao);
        loginService.createNewTablesIfNotExists();
        
    }
    
    @Test
    public void loginCanNotFindUser() throws SQLException{
        boolean notExists=loginService.login("usernameNotExists", "noPassWord");
        assertEquals(false, notExists);
    }
    @Test
    public void loginFindUser() throws SQLException{
        boolean isTrue=loginService.createNewUser("testFindname","testFindUser", "testFindPassword");
        boolean successLogin=loginService.login("testFindUser", "testFindPassword");
        assertEquals(true, successLogin);
    }
    
    @Test
    public void createNewUserReturnsTrue() throws SQLException{
        boolean mustBeTrue=loginService.createNewUser("Testname","Testusername", "Testpassword");
        assertEquals(true, mustBeTrue);
    }
    
    @Test
    public void createNewUserWhenUsernameExists() throws SQLException{
        boolean isTrue=loginService.createNewUser("OtherTestName","OtherUserName", "OtherTestpassword");
        boolean isFalse=loginService.createNewUser("Testing","OtherUserName", "OtherTestpassword");
        assertEquals(false, isFalse);
    }
    
    @After
    public void tearDown() throws SQLException {
        testuserdao.deleteDatabase("testuserDB");
        testcurriculumdao.deleteDatabase("testcurriculumDB");
    }

  
}
