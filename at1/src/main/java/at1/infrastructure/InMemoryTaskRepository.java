package at1.infrastructure;

import at1.domain.Task;
import at1.domain.TaskRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskRepository implements TaskRepository {
    private Map<Integer, Task> tasks;
    private int nextId;

    public InMemoryTaskRepository() {
        this.tasks = new LinkedHashMap<>();
        this.nextId = 1;
    }

    @Override
    public Task add(String name) {
        Task task = new Task(nextId, name);
        tasks.put(nextId, task);
        nextId++;
        return task;
    }

    @Override
    public Task findById(int id) throws IllegalArgumentException {
        Task task = tasks.get(id);
        if (task == null) {
            throw new IllegalArgumentException("Task #" + id + " not found.");
        }
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void markAsDone(int id) throws IllegalArgumentException {
        findById(id).setDone(true);
    }

    @Override
    public void delete(int id) throws IllegalArgumentException {
        Task task = findById(id);
        tasks.remove(task.getId());
    }

    @Override
    public void clear() {
        tasks.clear();
        nextId = 1;
    }

    @Override
    public void replaceAll(List<Task> newTasks) {
        clear();
        for (Task task : newTasks) {
            tasks.put(task.getId(), task);
            if (task.getId() >= nextId) {
                nextId = task.getId() + 1;
            }
        }
    }

    @Override
    public long calculateDoneCount() {
        return findAll().stream().filter(task -> task.isDone()).count();
    }

    @Override
    public long calculateTodoCount() {
        return findAll().stream().filter(task -> !task.isDone()).count();
    }

    @Override
    public long getTaskSize() {
        return findAll().size();
    }
}
