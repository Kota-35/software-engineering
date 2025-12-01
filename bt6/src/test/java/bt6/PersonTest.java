package bt6;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PersonTest {
    
    @Test
    public void testGetId() {
        Person person = new Person("p001", "Tanaka", "Taro");
        String expected = "p001";
        String result = person.getId();
        assertEquals(expected, result);
    }

    @Test
    public void testGetId2() {
        Person person = new Person("p002", "Suzuki", "Hanako");
        String expected = "p002";
        String result = person.getId();
        assertEquals(expected, result);
    }

    @Test
    public void testConstructor() {
        Person person = new Person("p003", "Yamada", "Ichiro");
        assertEquals("p003", person.getId());
    }
}

