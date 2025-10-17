package bt1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicTask1Test {
    
    @Test
    public void isRem1Test() {
        boolean result = BasicTask1.isRem1();

        assertEquals(result, true);
    }

    @Test
    public void isRem1Test2() {
        // 正しく判定されない最小の値
        String[] args = {"46341"};
        BasicTask1.preprocess(args);
        boolean result = BasicTask1.isRem1();

        assertEquals(false, result);
    }

    @Test
    public void isRem1Test3() {
        // 正しく判定される最大の値
        String[] args = {"46340"};
        BasicTask1.preprocess(args);
        boolean result = BasicTask1.isRem1();

        assertEquals(true, result);
    }
}
 