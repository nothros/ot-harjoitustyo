package curriculatorapp.dao;

import curriculatorapp.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    public void createUser(String name, String username, String password) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
        Statement s = db.createStatement();
        PreparedStatement p = db.prepareStatement("INSERT INTO users(name,username,password) VALUES (?,?,?)");
        p.setString(1, name);
        p.setString(2, username);
        p.setString(3, password);

        p.executeUpdate();
        System.out.println("Käyttäjä lisätty");
        p.close();

        ResultSet r = s.executeQuery("SELECT * FROM Users");
        while (r.next()) {
            System.out.println(r.getInt("id") + " " + r.getString("name") + " " + r.getString("password"));

        }
        r.close();
        s.close();
    }
    
    public User findByUsername(String username) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
        ResultSet rs;
        User user;
        try (PreparedStatement stmt = db.prepareStatement("SELECT * FROM users "
                + "WHERE username = ?")) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            user = new User(rs.getString("name"), rs.getString("username"), rs.getString("password"));
        }
        rs.close();
        
        return user;
    }

    public void createNewUserTable() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
        try (PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS users "
                + "(id INTEGER PRIMARY KEY, "
                + "name    VARCHAR(255), "
                + "username    VARCHAR(255) UNIQUE,  "
                + "password   VARCHAR(255))")) {
            stmt.executeUpdate();
        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }
}
