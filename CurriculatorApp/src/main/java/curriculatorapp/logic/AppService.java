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
 * Luokka Päänäkymän- ja Uuden opinnon lisäämisnäkymän toiminnallisuuteen.
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

    /**
     * Metodi uuden opinnon lisäämiseen.
     *
     * @param curriculumName tutkinnon nimi.
     * @param scope tutkinnon laajuus.
     * @param choice tutkinnon "mittariksi" valittu sana
     * @throws java.sql.SQLException
     */
    public void createCurriculum(String curriculumName, int scope, String choice) throws SQLException {
        curriculumDao.createCurriculum(loggedUser, curriculumName, scope, choice);
    }

    /**
     * Metodi uuden kurssin lisäämiseen.
     *
     * @param courseName Kurssin nimi.
     * @param scope Kurssin laajuus.
     * @throws java.sql.SQLException
     */
    public void createCourse(String courseName, int scope) throws SQLException {
        coursesDao.createCourse(curriculum, courseName, scope);
    }

    public boolean checkIfCurriculumExist() throws SQLException {
        curriculum = (Curriculum) findCurriculum();
        return curriculum != null;
    }

    /**
     * Metodi merkitsee kurssin tehdyksi tietokantaan.
     *
     * @param course päivitettävä kurssi
     * @param grade arvosana
     * @throws java.sql.SQLException
     */
    public void markDone(Course course, String grade) throws SQLException {
        coursesDao.updateCourse(course, grade);
    }

    public List<Course> findAllCourses() throws SQLException {
        return coursesDao.findAllCourses(curriculum);
    }

    public Curriculum findCurriculum() throws SQLException {
        curriculum = (Curriculum) curriculumDao.findCurriculum(loggedUser);
        return curriculum;
    }

    /**
     * Metodi jäljellä olevien kurssien laskemiseen.
     *
     * @return Palauttaa määrän sanana.
     * @throws java.sql.SQLException
     */
    public String calculateLeftCourses() throws SQLException {
        int done = coursesDoneAmount();
        int leftCourses = curriculum.getScope() - done;
        if (leftCourses < 0) {
            return "0";
        }

        return "" + leftCourses;
    }

    /**
     * Metodi keskiarvon laskemiseen.
     *
     * @return Palauttaa määrän sanana.
     * @throws java.sql.SQLException
     */
    public String calculateAverageGrade() throws SQLException {
        List<Course> done = coursesDone();
        int gradeSum = 0;
        int courseAmount = 0;
        for (Course c : done) {
            if (c.getCourseGrade().length() == 1) {
                gradeSum += Integer.valueOf(c.getCourseGrade());
                courseAmount += 1;
            }
        }

        if (courseAmount == 0) {
            return "0";
        }
        float avgNumber = (float) gradeSum / courseAmount;
        String avg = String.format("%.2f", avgNumber);
        return avg;

    }

    /**
     * Metodi suoritetun koulutuksen laskemiseen prosentteina.
     *
     * @return Palauttaa määrän desimaalina.
     * @throws java.sql.SQLException
     */
    public double getProgressPercent() throws SQLException {
        int done = coursesDoneAmount();
        int scope = curriculum.getScope();
        if(scope ==0){
            return 0;
        }
        double percent = (double) done / scope;

        return percent;
    }

    /**
     * Metodi suoritettujen kurssien määrän tarkistamiseen.
     *
     * @return Palauttaa määrän kokonaislukuna.
     * @throws java.sql.SQLException
     */
    public int coursesDoneAmount() throws SQLException {
        List<Course> doneCourses = coursesDone();
        int sum = 0;
        for (Course c : doneCourses) {
            sum += c.getCourseScope();
        }
        return sum;
    }

    /**
     * Metodi suoritettujen kurssien palauttamiseen
     *
     * @return Palauttaa kurssit listana.
     * @throws java.sql.SQLException
     */
    public List<Course> coursesDone() throws SQLException {
        List<Course> doneCourses = new ArrayList<>();
        List<Course> courses = findAllCourses();
        for (Course c : courses) {
            if (c.isDone()) {
                doneCourses.add(c);
            }

        }
        return doneCourses;
    }
}
