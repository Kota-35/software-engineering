package at1.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TaskTest {
    
    @Test
    public void testConstructor() {
        Task task = new Task(1, "Test Task");
        
        assertEquals(1, task.getId());
        assertEquals("Test Task", task.getName());
        assertFalse(task.isDone());
        assertNotNull(task.getCreatedAt());
    }

    @Test
    public void testConstructor2() {
        Task task = new Task(5, "Another Task");
        
        assertEquals(5, task.getId());
        assertEquals("Another Task", task.getName());
        assertFalse(task.isDone());
    }

    @Test
    public void testSetDone() {
        Task task = new Task(1, "Test Task");
        
        assertFalse(task.isDone());
        task.setDone(true);
        assertTrue(task.isDone());
    }

    @Test
    public void testSetDoneFalse() {
        Task task = new Task(1, "Test Task");
        task.setDone(true);
        
        assertTrue(task.isDone());
        task.setDone(false);
        assertFalse(task.isDone());
    }

    @Test
    public void testSetCreatedAt() {
        Task task = new Task(1, "Test Task");
        Date customDate = new Date(1000000000L);
        
        task.setCreatedAt(customDate);
        assertEquals(customDate, task.getCreatedAt());
    }

    @Test
    public void testToSaveString() {
        Task task = new Task(1, "Test Task");
        task.setDone(true);
        Date customDate = new Date(1000000000L);
        task.setCreatedAt(customDate);
        
        String result = task.toSaveString();
        String expected = "1,Test Task,true," + customDate;
        
        assertEquals(expected, result);
    }

    @Test
    public void testToSaveStringNotDone() {
        Task task = new Task(2, "Another Task");
        Date customDate = new Date(2000000000L);
        task.setCreatedAt(customDate);
        
        String result = task.toSaveString();
        String expected = "2,Another Task,false," + customDate;
        
        assertEquals(expected, result);
    }
}

