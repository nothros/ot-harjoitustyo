package curriculatorapp.domain;

import java.util.ArrayList;

/**
 * Opintoa kuvaava luokka.
 *
 */
public class Curriculum {

    private ArrayList<Course> courses;
    private String curriculumName, choice;
    private int id, scope;
    private final User user;

    public Curriculum(String curriculumName, String choice, int scope, User user) {
        this.curriculumName = curriculumName;
        this.choice = choice;
        this.scope = scope;
        this.user = user;
        this.courses = new ArrayList<>();
    }

    public Curriculum(int id, String curriculumName, String choice, int scope, User user) {
        this.id = id;
        this.curriculumName = curriculumName;
        this.choice = choice;
        this.scope = scope;
        this.user = user;
    }

    public int getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public String getCurriculumName() {
        return this.curriculumName;
    }

    public String getChoice() {
        return this.choice;
    }

    public int getScope() {
        return this.scope;
    }
}
