package curriculatorapp.domain;

import java.util.Objects;

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

   
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (this.done != other.done) {
            return false;
        }
        
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.done ? 1 : 0);
        hash = 71 * hash + this.id;
        return hash;
    }
    
    

}
