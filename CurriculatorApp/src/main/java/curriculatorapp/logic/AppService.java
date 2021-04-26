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
    private User loggedUser;

    public AppService(UserDao userdao) {
        this.userdao = userdao;  
    }
    
    public boolean login(String username, String password) throws SQLException {
        loggedUser = (User) userdao.findByUsername(username);
        if (loggedUser == null) {
            return false;
        }
        
        if (!loggedUser.checkPassword(password, loggedUser.getPassword())) {
            return false;
        }
        
        return true;
    }
    
    public User getLoggedUser(){
        return loggedUser;
        
    }
    
    public String getLoggedName(){
        return loggedUser.getName();
    }

    public boolean createNewUser(String name, String username, String password) throws SQLException {
        User user = (User) userdao.findByUsername(username);
        if (user == null) {
        User newUser = new User(name, username);
        newUser.setPassword(password);
        String cryptedPassword = newUser.getPassword();
        userdao.createUser(name, username, cryptedPassword);
        return true;
        }
        return false;
        
    }
    
    public void createNewUserTableIfNotExists() throws SQLException {
        userdao.createNewUserTable();
    }
    
 
    
    
    
    
}
