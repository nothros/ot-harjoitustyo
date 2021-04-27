/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ehorrosw
 */
public class CurriculumDao {

    public void createNewTable() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
        try (PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS curriculums "
                + "(curriculum_id INTEGER PRIMARY KEY, "
                + "user_id   INTEGER, "
                + "name    VARCHAR(255),  "
                + "studymeter   VARCHAR(255), "
                + " FOREIGN KEY (user_id) REFERENCES users (user_id))")) {
            stmt.executeUpdate();

        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }
}
