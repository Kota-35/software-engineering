package at1.service;

import java.io.IOException;
import java.util.List;

import at1.domain.Task;
import at1.domain.TaskRepository;
import at1.infrastructure.FileTaskStorage;

public class TaskService {
    private TaskRepository repository;
    private FileTaskStorage storage;

    public TaskService(TaskRepository repository, FileTaskStorage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    // タスクをストレージに保存
    public void save() throws IOException {
        List<Task> tasks = repository.findAll();
        storage.saveTasks(tasks);
    }

    public void load() throws IOException {
        List<Task> tasks = storage.loadTasks();
        repository.replaceAll(tasks);
    }

    // TODO: 完了率の計算など
}
