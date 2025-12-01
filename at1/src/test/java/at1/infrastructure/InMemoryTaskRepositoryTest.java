package at1.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at1.domain.Task;

public class InMemoryTaskRepositoryTest {
    
    private InMemoryTaskRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    public void testAdd() {
        Task task = repository.add("Test Task");
        
        assertNotNull(task);
        assertEquals(1, task.getId());
        assertEquals("Test Task", task.getName());
        assertFalse(task.isDone());
    }

    @Test
    public void testAddMultiple() {
        Task task1 = repository.add("Task 1");
        Task task2 = repository.add("Task 2");
        Task task3 = repository.add("Task 3");
        
        assertEquals(1, task1.getId());
        assertEquals(2, task2.getId());
        assertEquals(3, task3.getId());
    }

    @Test
    public void testFindById() {
        Task task = repository.add("Test Task");
        Task found = repository.findById(1);
        
        assertEquals(task.getId(), found.getId());
        assertEquals(task.getName(), found.getName());
    }

    @Test
    public void testFindByIdNotFound() {
        try {
            repository.findById(999);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Task #999 not found.", e.getMessage());
        }
    }

    @Test
    public void testFindAll() {
        repository.add("Task 1");
        repository.add("Task 2");
        repository.add("Task 3");
        
        List<Task> tasks = repository.findAll();
        assertEquals(3, tasks.size());
    }

    @Test
    public void testFindAllEmpty() {
        List<Task> tasks = repository.findAll();
        assertEquals(0, tasks.size());
    }

    @Test
    public void testMarkAsDone() {
        Task task = repository.add("Test Task");
        assertFalse(task.isDone());
        
        repository.markAsDone(1);
        assertTrue(task.isDone());
    }

    @Test
    public void testMarkAsDoneNotFound() {
        try {
            repository.markAsDone(999);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Task #999 not found.", e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        repository.add("Task 1");
        repository.add("Task 2");
        
        assertEquals(2, repository.findAll().size());
        repository.delete(1);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testDeleteNotFound() {
        try {
            repository.delete(999);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Task #999 not found.", e.getMessage());
        }
    }

    @Test
    public void testClear() {
        repository.add("Task 1");
        repository.add("Task 2");
        repository.add("Task 3");
        
        assertEquals(3, repository.findAll().size());
        repository.clear();
        assertEquals(0, repository.findAll().size());
        
        // ID採番がリセットされていることを確認
        Task newTask = repository.add("New Task");
        assertEquals(1, newTask.getId());
    }

    @Test
    public void testReplaceAll() {
        repository.add("Task 1");
        repository.add("Task 2");
        
        Task task1 = new Task(10, "Replaced Task 1");
        Task task2 = new Task(20, "Replaced Task 2");
        List<Task> newTasks = List.of(task1, task2);
        
        repository.replaceAll(newTasks);
        
        List<Task> tasks = repository.findAll();
        assertEquals(2, tasks.size());
        assertEquals(10, tasks.get(0).getId());
        assertEquals(20, tasks.get(1).getId());
    }

    @Test
    public void testCalculateDoneCount() {
        repository.add("Task 1");
        repository.add("Task 2");
        repository.add("Task 3");
        
        repository.markAsDone(1);
        repository.markAsDone(2);
        
        assertEquals(2, repository.calculateDoneCount());
    }

    @Test
    public void testCalculateDoneCountZero() {
        repository.add("Task 1");
        repository.add("Task 2");
        
        assertEquals(0, repository.calculateDoneCount());
    }

    @Test
    public void testCalculateTodoCount() {
        repository.add("Task 1");
        repository.add("Task 2");
        repository.add("Task 3");
        
        repository.markAsDone(1);
        
        assertEquals(2, repository.calculateTodoCount());
    }

    @Test
    public void testCalculateTodoCountZero() {
        repository.add("Task 1");
        repository.add("Task 2");
        
        repository.markAsDone(1);
        repository.markAsDone(2);
        
        assertEquals(0, repository.calculateTodoCount());
    }

    @Test
    public void testGetTaskSize() {
        assertEquals(0, repository.getTaskSize());
        
        repository.add("Task 1");
        assertEquals(1, repository.getTaskSize());
        
        repository.add("Task 2");
        assertEquals(2, repository.getTaskSize());
    }
}

