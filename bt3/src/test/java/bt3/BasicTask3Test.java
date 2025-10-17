package bt3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicTask3Test {
    
    @Test
    public void isPrimeTest1() {
        int n = 2;

        boolean actual = BasicTask3.isPrime(n);

        assertEquals(true, actual);
    }

    @Test
    public void isPrimeTest2() {
        int n = 10;

        boolean actual = BasicTask3.isPrime(n);

        assertEquals(false, actual);
    }

    @Test
    public void isPrimeTest3() {
        int n = 110;

        boolean actual = BasicTask3.isPrime(n);

        assertEquals(false, actual);
    }

    @Test
    public void isPrimeTest4() {
        int n = 151;

        boolean actual = BasicTask3.isPrime(n);

        assertEquals(true, actual);
    }

    @Test
    public void isPrimeTest5() {
        int n = 9;

        boolean actual = BasicTask3.isPrime(n);

        assertEquals(false, actual);
    }

    @Test
    public void getPrimeNumberArrayTest1() {
        int[] numbers = {7, 8, 9, 10, 11};

        int[] expected = {7, 11};
        int[] actual = BasicTask3.getPrimeNumberArray(numbers);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getPrimeNumberArrayTest2() {
        int[] numbers = {42, 17, 23, 4, 29, 30, 19, 20};

        int[] expected = {17, 23, 29, 19};
        int[] actual = BasicTask3.getPrimeNumberArray(numbers);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getPrimeNumberArrayTest3() {
        int[] numbers = {121, 2, 97, 121, 89, 90, 97, 1000, 3};

        int[] expected = {2, 97, 89, 97, 3};
        int[] actual = BasicTask3.getPrimeNumberArray(numbers);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getPrimeNumberArrayTest4() {
        int[] numbers = {250, 251, 37, 38, 997, 998, 999, 1009};

        int[] expected = {251, 37, 997, 1009};
        int[] actual = BasicTask3.getPrimeNumberArray(numbers);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getPrimeNumberArrayTest5() {
        int[] numbers = {2, 49, 83, 64, 127, 143, 169, 173};

        int[] expected = {2, 83, 127, 173};
        int[] actual = BasicTask3.getPrimeNumberArray(numbers);

        assertArrayEquals(expected, actual);
    }
}
