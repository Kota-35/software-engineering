package at1.domain;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void add(String name);
    
    Optional<Task> findById(int id);
    
    List<Task> findAll();

    void markAsDone(int id);

    void delete(int id);

    void clear();

    void replaceAll(List<Task> tasks);
}
