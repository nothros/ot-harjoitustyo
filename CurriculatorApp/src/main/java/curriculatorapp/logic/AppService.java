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

    UserDao userdao;

    public AppService(UserDao userdao) {
        this.userdao = userdao;
    }

    public void createNewUser(String name, String username, String password) throws SQLException {
        User newUser = new User(name, username);
        newUser.setPassword(password);
        String hashedPassword = newUser.getPassword();
        userdao.createUser(name, username, hashedPassword);
    }
    
    
    
    
}
