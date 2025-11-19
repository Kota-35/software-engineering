package at1.domain;

import java.util.List;


public interface TaskRepository {
    Task add(String name);

    Task findById(int id) throws IllegalArgumentException;

    List<Task> findAll();

    void markAsDone(int id) throws IllegalArgumentException;

    void delete(int id) throws IllegalArgumentException;

    void clear();

    void replaceAll(List<Task> tasks);

    long calculateDoneCount();

    long calculateTodoCount();

    long getTaskSize();
}
