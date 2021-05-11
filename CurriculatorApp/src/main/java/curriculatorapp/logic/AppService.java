package curriculatorapp.logic;

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public String calculateLeftCourses() throws SQLException{
        int done = coursesDoneAmount();
        int leftCourses= curriculum.getScope() - done;
        if(leftCourses < 0){
            return "0";
        }
        return ""+leftCourses;
        
    }
    
    public String calculateAverageGrade() throws SQLException{
        List<Course> done = coursesDone();
        int gradeSum=0;
        int courseAmount=0;
        for(Course c: done){
            if(c.getCourseGrade().length()==1){
                System.out.println("ON PIDEMPI" +c.getCourseGrade());
                gradeSum+=Integer.valueOf(c.getCourseGrade());
                courseAmount+=1;
            }
        }
        if (courseAmount == 0){
            return "0";
        }
        System.out.println(courseAmount);
        System.out.println("SUM "+gradeSum);
        float avgNumber = (float)gradeSum/courseAmount;
        System.out.println(avgNumber);
        String avg= String.format("%.2f", avgNumber);
        return avg;
        
    }
    public double getProgressPercent() throws SQLException{
         int done = coursesDoneAmount();
         int scope = curriculum.getScope();
         double percent=(double) done/scope;
         
         return percent;
    }
    
    public int coursesDoneAmount() throws SQLException{
         List<Course> doneCourses=coursesDone();
         int sum=0;
         for(Course c: doneCourses){
             sum+=c.getCourseScope();
         }
         System.out.print(sum);
         return sum;
    }
    
    public List<Course>  coursesDone() throws SQLException{
        List<Course> doneCourses=new ArrayList<>();
        List<Course> courses=findAllCourses();
        for (Course c : courses){
            if (c.isDone()){
                doneCourses.add(c);
                
            }
        }
        System.out.print(doneCourses);
        return doneCourses;
    }
}
