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
public class CurriculumDao {

    private final Connection conn;

    public CurriculumDao(String url) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + url);

    }

    public void createNewTable() throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Curriculums "
                + "(curriculum_id INTEGER PRIMARY KEY, "
                + "user_id   INTEGER, "
                + "curriculum_name    VARCHAR(255),  "
                + "studymeter   VARCHAR(255), "
                + " FOREIGN KEY (user_id) REFERENCES users (user_id))")) {
            stmt.executeUpdate();

        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }

    public void createCurriculum(String loggedUsername, String curriculumName, String studymeter) throws SQLException {
        System.out.println("Lisätään ");
        PreparedStatement p1 = conn.prepareStatement("SELECT user_id FROM Users WHERE username=?");
        p1.setString(1, loggedUsername);
        ResultSet r = p1.executeQuery();
        if (r.next()) {
            PreparedStatement p2 = conn.prepareStatement("INSERT INTO Curriculums (user_id,curriculum_name,studymeter) VALUES (?,?,?)");
            p2.setInt(1, r.getInt("user_id"));
            p2.setString(2, curriculumName);
            p2.setString(3, studymeter);
            p2.executeUpdate();
        }
    }

    public boolean findCurriculum(String loggedUsername) throws SQLException {
        boolean exists = false;
        PreparedStatement p1 = conn.prepareStatement("SELECT user_id FROM Users WHERE username=?");
        p1.setString(1, loggedUsername);
        try (ResultSet r1 = p1.executeQuery()) {
            if (r1.next()) {
                PreparedStatement p2 = conn.prepareStatement("SELECT * FROM Curriculums WHERE user_id=?");
                p2.setInt(1, r1.getInt("user_id"));
                try (ResultSet r2 = p2.executeQuery()) {
                    if (r2.next()) {
                        String name = r2.getString("curriculum_name");
                        System.out.println(name);
                        exists = true;
                    }
                }
            }
        }

        return exists;
    }
}
