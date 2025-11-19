package at1.infrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import at1.domain.Task;
import at1.domain.TaskRepository;

public class InMemoryTaskRepository implements TaskRepository {
    private Map<Integer, Task> tasks;
    private int nextId;

    public InMemoryTaskRepository() {
        this.tasks = new LinkedHashMap<>();
        this.nextId = 1;
    }

    @Override
    public void add(String name) {
        tasks.put(nextId, new Task(nextId, name));
        nextId++;
    }

    @Override
    public Optional<Task> findById(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void markAsDone(int id) {
        findById(id).ifPresent(task -> task.setDone(true));
    }

    @Override
    public void delete(int id) {
        tasks.remove(id);
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
}
