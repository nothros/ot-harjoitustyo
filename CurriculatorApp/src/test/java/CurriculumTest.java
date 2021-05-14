/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
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
public class CurriculumTest {
    
    @Test
    public void notEqualWhenDifferentUserID() {
        User u1 = new User(1, "t", "t", "t");
        User u2 = new User(3, "t", "t", "t");
        Curriculum c1 = new Curriculum(null, null, 0, u1);
        Curriculum c2 = new Curriculum(null, null, 0, u2);
        assertFalse(c1.equals(c2));
    }
    
    @Test
    public void notEqualWhenDifferentID() {
        User u1 = new User(1, "t", "t", "t");
        Curriculum c1 = new Curriculum(1,null, null, 0, u1);
        Curriculum c2 = new Curriculum(5,null, null, 0, u1);
        assertFalse(c1.equals(c2));
    }
    
        @Test
    public void EqualWhenSameUserSameID() {
        User u1 = new User(1, "t", "t", "t");
        Curriculum c1 = new Curriculum(1,null, null, 0, u1);
        Curriculum c2 = new Curriculum(1,null, null, 0, u1);
        assertTrue(c1.equals(c2));
    }
    
     @Test
    public void nonEqualWhenDifferentType() {
        User u1 = new User(1, "t", "t", "t");
        Curriculum c1 = new Curriculum(1,null, null, 0, u1);
        Object o = new Object();
        assertFalse(c1.equals(o));
    }      
}
