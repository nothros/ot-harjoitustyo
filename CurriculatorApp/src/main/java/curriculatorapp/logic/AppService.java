/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.logic;

import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.User;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ehorrosw
 */
public class AppService {

    private UserDao userdao;

    public AppService(UserDao userdao) {
        this.userdao = userdao;  
    }
    
    public boolean login(String username, String password) throws SQLException {
        User user = (User) userdao.findByUsername(username);
        if (user == null) {
            return false;
        }
        
        if (!user.checkPassword(password, user.getPassword())) {
            return false;
        }
        
        return true;
    }

    public void createNewUser(String name, String username, String password) throws SQLException {
        User newUser = new User(name, username);
        newUser.setPassword(password);
        String hashedPassword = newUser.getPassword();
        userdao.createUser(name, username, hashedPassword);
    }
    
    public void createNewUserTableIfNotExists() throws SQLException {
        userdao.createNewUserTable();
    }
    
 
    
    
    
    
}
