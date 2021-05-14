/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import curriculatorapp.domain.Course;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ehorrosw
 */
public class CourseTest {

    @Test
    public void equalWhenSameId() {
        Course c1 = new Course(1, null, true, 0, null);
        Course c2 = new Course(1, null, true, 0, null);
        assertTrue(c1.equals(c2));
    }

    @Test
    public void noEqualWhenDifferentId() {
        Course c1 = new Course(1, null, true, 0, null);
        Course c2 = new Course(55, null, true, 0, null);
        assertFalse(c1.equals(c2));
    }

    @Test
    public void nonEqualWhenDifferentType() {
        Course c = new Course(1, null, true, 0, null);
        Object o = new Object();
        assertFalse(c.equals(o));
    }
}
