package curriculatorapp.domain;

/**
 * Kurssia kuvaava luokka.
 */
public class Course {

    private String courseName;
    private boolean done;
    private int courseScope;
    private String courseGrade;
    private int id;

    public Course(String courseName, int courseScope) {
        this.courseName = courseName;
        this.courseScope = courseScope;
        this.done = false;
        this.courseGrade = "";
    }

    public Course(int id, String courseName, boolean done, int courseScope, String courseGrade) {
        this.id = id;
        this.courseName = courseName;
        this.courseScope = courseScope;
        this.done = done;
        this.courseGrade = courseGrade;
    }

    public int getId() {
        return this.id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getCourseScope() {
        return courseScope;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseScope(int courseScope) {
        this.courseScope = courseScope;
    }

}
