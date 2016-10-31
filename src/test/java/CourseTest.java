import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() throws Exception {

    }

    @Test
    public void equal() throws Exception {
        Course c1 = new Course(1);
        Course c2 = new Course(2);
        Course c1_1 = new Course(1);
        assertEquals(false, c1.equals(c2));
        assertEquals(true, c1.equals(c1_1));
        assertEquals(true, c1.equals((Integer)1));
        assertEquals(false, c1.equals((Integer)2));
        assertEquals(true, c1.equals(1));
        assertEquals(false, c1.equals(2));
    }

}