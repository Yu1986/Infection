import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jy on 10/30/2016.
 */
public class PeopleTest {
    @Test
    public void equals() throws Exception {
        People c1 = new People(1);
        People c2 = new People(2);
        People c1_1 = new People(1);
        assertEquals(false, c1.equals(c2));
        assertEquals(true, c1.equals(c1_1));
        assertEquals(true, c1.equals((long)1));
        assertEquals(false, c1.equals((long)2));
        assertEquals(false, c1.equals(1));
        assertEquals(false, c1.equals(2));
    }

}