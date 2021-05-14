package curriculatorapp.dao;

import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka Courses-tietokantataulun toiminnalle.
 */
public class CoursesDao {

    private final Connection conn;

    public CoursesDao(String url) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + url);

    }

    /**
     * Metodi lisää uuden Courses- tietokantataulun
     *
     * @throws java.sql.SQLException
     */
    public void createNewTable() throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Courses "
                + "(course_id INTEGER PRIMARY KEY, "
                + "curriculum_id   INTEGER, "
                + "coursename    VARCHAR(255),  "
                + "scope  INTEGER, "
                + "grade  VARCHAR(255), "
                + "done   BOOLEAN, "
                + " FOREIGN KEY (curriculum_id) REFERENCES Curriculums (curriculum_id))")) {
            stmt.executeUpdate();

        }

    }

    /**
     * Metodi lisää kurssin tauluun.
     *
     * @param userCurriculum Käyttäjän opinnot
     * @param coursename käyttäjän lisäämä kurssin nimi
     * @param scope käyttäjän valitsema laajuus.
     * @throws java.sql.SQLException
     */
    public void createCourse(Curriculum userCurriculum, String coursename, int scope) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("INSERT INTO Courses (curriculum_id,coursename,scope,grade,done) VALUES (?,?,?,?,?)")) {
            stmt.setInt(1, userCurriculum.getId());
            stmt.setString(2, coursename);
            stmt.setInt(3, scope);
            stmt.setString(4, "");
            stmt.setBoolean(5, false);
            stmt.executeUpdate();

        }

    }

    /**
     * Metodi päivittää opintojen kurssin
     *
     * @param course kurssi
     * @param grade arvosana
     * @throws java.sql.SQLException
     */
    public void updateCourse(Course course, String grade) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("UPDATE Courses SET grade=?, done=? WHERE course_id=?")) {
            stmt.setString(1, grade);
            stmt.setBoolean(2, true);
            stmt.setInt(3, course.getId());

            stmt.executeUpdate();
        }

    }

    /**
     * Metodi palauttaa opintojen kaikki kurssit listassa
     *
     * @param userCurriculum Käyttäjän opinnot
     * @return palauttaa kurssit listassa
     * @throws java.sql.SQLException
     */
    public List<Course> findAllCourses(Curriculum userCurriculum) throws SQLException {
        List<Course> courses = new ArrayList<>();
        Course course;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Courses WHERE curriculum_id=?")) {
            stmt.setInt(1, userCurriculum.getId());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                course = new Course(rs.getInt("course_id"), rs.getString("coursename"), rs.getBoolean("done"), rs.getInt("scope"), rs.getString("grade"));
                courses.add(course);
            }
        }
        return courses;

    }
    
        public Course findOneCourse(Curriculum userCurriculum, String courseName) throws SQLException {
        Course course = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Courses WHERE curriculum_id=?")) {
            stmt.setInt(1, userCurriculum.getId());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if(rs.getString("coursename").equals(courseName)){
                     course = new Course(rs.getInt("course_id"), rs.getString("coursename"), rs.getBoolean("done"), rs.getInt("scope"), rs.getString("grade"));
                }
               
   
            }
        }
        return course;

    }

    /**
     * Metodi poistaa koko tietokannan.Tarkoitettu testien käyttöön
     *
     * @param database poistettavan tietokannan nimi
     * @throws java.sql.SQLException
     */
    public void deleteDatabase(String database) throws SQLException {
        conn.close();
        File deletedDB = new File(database + ".db");
        deletedDB.delete();

    }
}
