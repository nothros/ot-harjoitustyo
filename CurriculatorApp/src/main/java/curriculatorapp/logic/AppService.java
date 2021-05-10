
package curriculatorapp.logic;

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ehorrosw
 */
public class AppService implements Service {

    private CurriculumDao curriculumDao;
    private User loggedUser;
    private Curriculum curriculum;
    private CoursesDao coursesDao;

    public AppService(User loggedUser, CurriculumDao curriculumdao, CoursesDao coursesdao) throws SQLException {
        this.curriculumDao = curriculumdao;
        this.loggedUser = loggedUser;
        this.coursesDao = coursesdao;
        
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void createCurriculum(String curriculumName, int scope, String choice) throws SQLException {
        curriculumDao.createCurriculum(loggedUser, curriculumName, scope, choice);
    }
    
    public void createCourse(String courseName, int scope) throws SQLException {
        coursesDao.createCourse(curriculum, courseName, scope);
        System.out.print("kurssi lisätty");
              System.out.println(coursesDao.findAllCourses(curriculum));
    }

    public boolean checkIfCurriculumExist() throws SQLException {
        curriculum = (Curriculum) findCurriculum();
        return curriculum != null;
    }
    
    public void markDone(Course course, String grade) throws SQLException{
       coursesDao.updateCourse(course, grade);
    }
    
    public List<Course> findAllCourses() throws SQLException{
        return coursesDao.findAllCourses(curriculum);
    }

    public Curriculum findCurriculum() throws SQLException {
        curriculum = (Curriculum) curriculumDao.findCurriculum(loggedUser);
        return curriculum;
    }
}
