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
    String name = "TestName";
    String username= "TestUser";
    String password = "Password";
    
    @Before
    public void setUpClass() {
        
        user = new User(name, username, password);
    }
    
    

}
