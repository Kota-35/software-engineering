package bt7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {
    
    @Test
    public void testConstructor() {
        Player player = new Player("Test Player");
        assertEquals("Test Player", player.getName());
    }

    @Test
    public void testConstructor2() {
        Player player = new Player("Alice");
        assertEquals("Alice", player.getName());
    }

    @Test
    public void testGetName() {
        Player player = new Player("Bob");
        String expected = "Bob";
        String result = player.getName();
        assertEquals(expected, result);
    }

    @Test
    public void testAddItem() {
        Player player = new Player("Test Player");
        Item item = new Item("Test Item");
        
        // addItemが例外をスローしないことを確認
        player.addItem(item);
        assertEquals("Test Player", player.getName());
    }

    @Test
    public void testAddItemMultiple() {
        Player player = new Player("Test Player");
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        Item item3 = new Item("Item 3");
        Item item4 = new Item("Item 4");
        
        // 最大4つまで追加可能
        player.addItem(item1);
        player.addItem(item2);
        player.addItem(item3);
        player.addItem(item4);
        
        assertEquals("Test Player", player.getName());
    }

    @Test
    public void testAddItemOverLimit() {
        Player player = new Player("Test Player");
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        Item item3 = new Item("Item 3");
        Item item4 = new Item("Item 4");
        Item item5 = new Item("Item 5");
        
        // 4つまで追加
        player.addItem(item1);
        player.addItem(item2);
        player.addItem(item3);
        player.addItem(item4);
        
        // 5つ目は追加できない（例外はスローされない）
        player.addItem(item5);
        
        assertEquals("Test Player", player.getName());
    }

    @Test
    public void testAddItemDuplicate() {
        Player player = new Player("Test Player");
        Item item = new Item("Test Item");
        
        // 同じアイテムを2回追加（2回目は追加されない）
        player.addItem(item);
        player.addItem(item);
        
        assertEquals("Test Player", player.getName());
    }
}

