/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.dao;

import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        try ( PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS Courses "
                + "(course_id INTEGER PRIMARY KEY, "
                + "user_id   INTEGER, "
                + "coursename    VARCHAR(255),  "
                + "scope  INTEGER, "
                + "grade  VARCHAR(255), "
                + "done   BOOLEAN, "
                + " FOREIGN KEY (user_id) REFERENCES Users (user_id))")) {
            stmt.executeUpdate();

        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }

    public void createCourse(User loggedUser, String coursename, int scope) throws SQLException {
        System.out.println("Lisätään ");
        try ( PreparedStatement stmt = conn.prepareStatement("INSERT INTO Courses (user_id,coursename,scope,grade,done) VALUES (?,?,?,?,?)")) {
            stmt.setInt(1, loggedUser.getId());
            stmt.setString(2, coursename);
            stmt.setInt(3, scope);
            stmt.setString(4, "");
            stmt.setBoolean(5, false);
            stmt.executeUpdate();

        }

    }

    public void updateCourse(Course course, String grade) throws SQLException {
        System.out.println("päivitetään ");
    
                try(PreparedStatement stmt = conn.prepareStatement("UPDATE Courses SET grade=?, done=? WHERE course_id=?")){
                stmt.setString(1, grade);
                stmt.setBoolean(2, true);
                stmt.setInt(3, course.getId());

                stmt.executeUpdate();
                }

    }

    public List<Course> findAllCourses(User loggedUser) throws SQLException {
        List<Course> courses = new ArrayList<>();
        Course course;

        try ( PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Courses WHERE user_id=?")) {
            stmt.setInt(1, loggedUser.getId());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                course = new Course(rs.getInt("course_id"), rs.getString("coursename"), rs.getBoolean("done"), rs.getInt("scope"), rs.getString("grade"));
                courses.add(course);
            }
        }
        return courses;

    }
}
