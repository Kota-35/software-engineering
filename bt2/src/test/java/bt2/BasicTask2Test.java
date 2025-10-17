package bt2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicTask2Test {
    
    @Test
    public void permTest1() {

        int n = 5;
        int r = 3;

        int expected = BasicTask2.perm(n, r);

        assertEquals(60, expected);
    }

    @Test
    public void permTest2() {

        int n = 100;
        int r = -1;

        int expected = BasicTask2.perm(n, r);

        assertEquals(0, expected);
    }

    @Test
    public void permTest3() {

        int n = 6;
        int r = 3;

        int expected = BasicTask2.perm(n, r);

        assertEquals(120, expected);
    }

    @Test
    public void permTest4() {

        int n = 7;
        int r = 3;

        int expected = BasicTask2.perm(n, r);

        assertEquals(210, expected);
    }
}