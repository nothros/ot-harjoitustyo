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
public class CoursesDao {

    public void createNewTable() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
        try (PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS courses "
                + "(course_id INTEGER PRIMARY KEY, "
                + "curriculum_id   INTEGER, "
                + "coursename    VARCHAR(255),  "
                + "scope  INTEGER, "
                + " FOREIGN KEY (curriculum_id ) REFERENCES curriculums (curriculum_id ))")) {
            stmt.executeUpdate();

        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }
}
