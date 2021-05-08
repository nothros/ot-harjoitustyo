/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ehorrosw
 */
public class CoursesDao {
    private final Connection conn;

    public CoursesDao(String url) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + url);

    }

    public void createNewTable() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
        try (PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS courses "
                + "(course_id INTEGER PRIMARY KEY, "
                + "curriculum_id   INTEGER, "
                + "coursename    VARCHAR(255),  "
                + "scope  INTEGER, "
                + " FOREIGN KEY (curriculum_id) REFERENCES Curriculums (curriculum_id))")) {
            stmt.executeUpdate();

        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }
     public void createCourse(String loggedUsername, String coursename, int scope) throws SQLException {
        System.out.println("Lisätään ");
        PreparedStatement p1 = conn.prepareStatement("SELECT user_id FROM Users WHERE username=?");
        p1.setString(1, loggedUsername);
        ResultSet r1 = p1.executeQuery();
        p1.close();
        if (r1.next()) {
            PreparedStatement p2 = conn.prepareStatement("SELECT curriculum_id FROM Curriculums WHERE user_id=?");
            p2.setInt(1, r1.getInt("user_id"));
            
        ResultSet r2 = p2.executeQuery();
        p2.close();
         if (r2.next()) {
            PreparedStatement p3 = conn.prepareStatement("INSERT INTO Courses (curriculum_id,coursename,scope) VALUES (?,?,?)");
            p3.setInt(1, r2.getInt("curriculum_id"));
            p3.setString(2, coursename);
            p3.setInt(3, scope);
            p3.executeUpdate();
            p3.close();
            
        }
         r2.close();
    }
        r1.close();
     }
}
