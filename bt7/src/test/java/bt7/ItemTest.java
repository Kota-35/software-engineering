package bt7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemTest {

    @Test
    public void testConstructor() {
        Item item = new Item("Test Item");
        assertEquals("Test Item", item.getName());
    }

    @Test
    public void testConstructor2() {
        Item item = new Item("Sword");
        assertEquals("Sword", item.getName());
    }

    @Test
    public void testGetName() {
        Item item = new Item("Potion");
        String expected = "Potion";
        String result = item.getName();
        assertEquals(expected, result);
    }
}

