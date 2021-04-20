package curriculatorapp.dao;

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

    public static void initTestipoyta() throws SQLException {
        /*  * * *
        NÄMÄ EI JÄÄ TÄNNE    
            * * *  */

        Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");

        PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name    VARCHAR(255), username    VARCHAR(255),  password   VARCHAR(255))");
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }
}
