package domain;

import curriculatorapp.domain.Course;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testit Course oliolle.
 */
public class CourseTest {

    /**
     * Testaa että samat id:t on sama kurssi.
     */
    @Test
    public void equalWhenSameId() {
        Course c1 = new Course(1, null, true, 0, null);
        Course c2 = new Course(1, null, true, 0, null);
        assertTrue(c1.equals(c2));
    }

    /**
     * Testaa että eri id:t ei ole sama kurssi.
     */
    @Test
    public void noEqualWhenDifferentId() {
        Course c1 = new Course(1, null, true, 0, null);
        Course c2 = new Course(55, null, true, 0, null);
        assertFalse(c1.equals(c2));
    }

    /**
     * Testaa että eri tyyppi ei ole course.
     */
    @Test
    public void nonEqualWhenDifferentType() {
        Course c = new Course(1, null, true, 0, null);
        Object o = new Object();
        assertFalse(c.equals(o));
    }
}
