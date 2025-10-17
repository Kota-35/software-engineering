package bt1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicTask1Test {
    
    @Test
    public void isRem1Test() {
        boolean result = BasicTask1.isRem1();

        assertEquals(result, true);
    }
}
 