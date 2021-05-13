package curriculatorapp.domain;

import java.util.ArrayList;
import java.util.Objects;

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
        final Curriculum other = (Curriculum) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.scope != other.scope) {
            return false;
        }
        if (!Objects.equals(this.choice, other.choice)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.choice);
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.scope;
        hash = 79 * hash + Objects.hashCode(this.user);
        return hash;
    }
    
}
