package domain;

import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testit Curriculum oliolle.
 */
public class CurriculumTest {

    /**
     * Testaa että eri käyttäjät eivät voi olla sama curriculum.
     */
    @Test
    public void notEqualWhenDifferentUserID() {
        User u1 = new User(1, "t", "t", "t");
        User u2 = new User(3, "t", "t", "t");
        Curriculum c1 = new Curriculum(null, null, 0, u1);
        Curriculum c2 = new Curriculum(null, null, 0, u2);
        assertFalse(c1.equals(c2));
    }

    /**
     * Testaa että eri curriculumit eivät voi olla sama.
     */
    @Test
    public void notEqualWhenDifferentID() {
        User u1 = new User(1, "t", "t", "t");
        Curriculum c1 = new Curriculum(1, null, null, 0, u1);
        Curriculum c2 = new Curriculum(5, null, null, 0, u1);
        assertFalse(c1.equals(c2));
    }

    /**
     * Testaa että sama curriculum on samas.
     */
    @Test
    public void EqualWhenSameUserSameID() {
        User u1 = new User(1, "t", "t", "t");
        Curriculum c1 = new Curriculum(1, null, null, 0, u1);
        Curriculum c2 = new Curriculum(1, null, null, 0, u1);
        assertTrue(c1.equals(c2));
    }

    /**
     * Testaa että eri tyyppi ei voi olla sama.
     */
    @Test
    public void nonEqualWhenDifferentType() {
        User u1 = new User(1, "t", "t", "t");
        Curriculum c1 = new Curriculum(1, null, null, 0, u1);
        Object o = new Object();
        assertFalse(c1.equals(o));
    }
}
