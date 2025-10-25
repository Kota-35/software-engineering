package bt4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberSlimeTest {
    

    @Test
    public void constructorTest() {
        int num = 10;
        NumberSlime tmp = new NumberSlime(num);

        assertEquals(num, tmp.getNumber());

        int changedNum = 20;

        tmp.setNumber(changedNum);

        assertEquals(changedNum, tmp.getNumber());
    }

    @Test
    public void absorbTest1() {
        NumberSlime a = new NumberSlime(6);
        NumberSlime b = new NumberSlime(4);

        a.absorb(b);

        assertEquals(10, a.getNumber());
        assertEquals(0, b.getNumber());
    }

    @Test
    public void absorbTest2() {
        NumberSlime a = new NumberSlime(9);
        NumberSlime b = new NumberSlime(8);

        a.absorb(b);
        assertEquals(17, a.getNumber());
        assertEquals(0, b.getNumber());
    }

    @Test
    public void splitTest1() {
        NumberSlime a = new NumberSlime(6);

        NumberSlime b = a.split();

        assertEquals(3, a.getNumber());
        assertEquals(3, b.getNumber());
    }

    @Test
    public void splitTest2() {
        NumberSlime a = new NumberSlime(23);

        NumberSlime b = a.split();

        assertEquals(11, a.getNumber());
        assertEquals(11, b.getNumber());
    }

    @Test
    public void averageTest1() {
        NumberSlime a = new NumberSlime(6);
        NumberSlime b = new NumberSlime(4);

        a.average(b);

        assertEquals(5, a.getNumber());
        assertEquals(5, b.getNumber());
    }

    @Test
    public void averageTest2() {
        NumberSlime a = new NumberSlime(16);
        NumberSlime b = new NumberSlime(5);

        a.average(b);

        assertEquals(10, a.getNumber());
        assertEquals(10, b.getNumber());
    }
}
