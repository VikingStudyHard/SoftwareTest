import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testTriangle() throws Exception {
        int a,b,c;

        a = 2;
        b = 2;
        c = 2;
        assertEquals(("illegal triangle"),Main.testTriangle(a,b,c));

    }

}