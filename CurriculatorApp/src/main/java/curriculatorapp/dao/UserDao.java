package curriculatorapp.dao;

import curriculatorapp.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    private final Connection conn;
    
    public UserDao(String url) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + url);
        
    }
    public void createUser(String name, String username, String password) throws SQLException {
        System.out.println("Lisätään ");
        PreparedStatement p = conn.prepareStatement("INSERT INTO users(name,username,password, curriculum) VALUES (?,?,?,?)");
        p.setString(1, name);
        p.setString(2, username);
        p.setString(3, password);
        p.setString(4, "");
        p.executeUpdate();
        System.out.println("Käyttäjä lisätty");
        p.close();
    }

    public User findByUsername(String username) throws SQLException {
        ResultSet rs;
        User user;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users "
                + "WHERE username = ?")) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            user = new User(rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("curriculum"));
        }
        rs.close();

        return user;
    }

    public void createNewUserTable() throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users "
                + "(user_id INTEGER PRIMARY KEY, "
                + "name    VARCHAR(255), "
                + "username    VARCHAR(255),  "
                + "password   VARCHAR(255), "
                + "curriculum   VARCHAR(255))")) {
            stmt.executeUpdate();

        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }

    public void dropTable() throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DROP TABLE users")) {
            stmt.executeUpdate();
        }
    }

  
}
