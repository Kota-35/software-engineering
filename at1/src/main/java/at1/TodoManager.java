package at1;

import at1.domain.TaskRepository;
import at1.infrastructure.FileTaskStorage;
import at1.infrastructure.InMemoryTaskRepository;
import at1.service.TaskService;
import at1.ui.CommandHandler;

public class TodoManager {
    public void run() {

        TaskRepository repository = new InMemoryTaskRepository();
        FileTaskStorage storage = new FileTaskStorage();
        TaskService service = new TaskService(repository, storage);
        CommandHandler handler = new CommandHandler(repository, service);

        handler.run();
    }
}
