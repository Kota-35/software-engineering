package at1.ui;

import at1.domain.Task;
import at1.domain.TaskRepository;
import at1.service.TaskService;
import at1.util.ConsoleColors;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CommandHandler {
    private TaskRepository repository;
    private TaskService service;
    private Scanner scanner;

    public CommandHandler(TaskRepository repository, TaskService service) {
        this.repository = repository;
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            // [UX向上] プロンプト表示を追加
            System.out.print(ConsoleColors.prompt("TODO> "));
            String input = scanner.nextLine().trim();
            if (input.isEmpty())
                continue;

            String[] parts = input.split(" ", 2);
            String command = parts[0];

            try {
                if (command.equals("exit")) {
                    break;
                } else if (command.equals("post") && parts.length == 2) {
                    handlePost(parts[1]);
                } else if (command.equals("done") && parts.length == 2) {
                    handleDone(parts[1]);
                } else if (command.equals("show")) {
                    handleShow();
                } else if (command.equals("delete") && parts.length == 2) {
                    handleDelete(parts[1]);
                } else if (command.equals("save")) {
                    handleSave();
                } else if (command.equals("load")) {
                    handleLoad();
                } else if (command.equals("help")) {
                    handleHelp();
                } else {
                    System.out.println("Command: " + command + " not found.");
                }
            } catch (Exception e) {
                handleError(e);
            }
        }
    }

    private void handlePost(String name) {
        Task addedTask = repository.add(name);
        System.out.println(ConsoleColors.success("✓") + " Added task #" + addedTask.getId() + " "
                + addedTask.getName());
    }

    private void handleDone(String idStr) {
        try {
            int id = Integer.parseInt(idStr);

            repository.markAsDone(id);
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.error("Error: ") + "Invalid ID " + idStr
                    + ". ID must be a number.");
        } catch (IllegalArgumentException e) {
            System.out.println(ConsoleColors.error("Error: ") + e.getMessage()
                    + " Use 'show' to see available tasks.");
        }

    }

    private void handleShow() {
        List<Task> tasks = repository.findAll();
        String headerText = ConsoleColors.colorize("Tasks: " + tasks.size() + " total, ",
                ConsoleColors.BOLD + ConsoleColors.CYAN)
                + ConsoleColors.colorize(repository.calculateDoneCount() + " done, ",
                        ConsoleColors.GREEN + ConsoleColors.BOLD)
                + ConsoleColors.colorize(repository.calculateNotDoneCount() + " not done\n",
                        ConsoleColors.BOLD + ConsoleColors.YELLOW);
        System.out.println(headerText);

        for (Task task : tasks) {
            String checkBox = task.isDone() ? ConsoleColors.success("[✓]") : "[ ]";

            System.out.println(checkBox + " "
                    + ConsoleColors.colorize(task.getId() + ":", ConsoleColors.BLUE) + " "
                    + task.getName() + " " + (task.isDone() ? ConsoleColors.success("DONE!") : ""));
        }
    }

    private void handleDelete(String idStr) {

        try {
            int id = Integer.parseInt(idStr);

            // タスクの存在確認
            Task task = repository.findById(id);

            // 確認プロンプト表示
            System.out.print(ConsoleColors.prompt("Delete task #" + id + ": \"") + task.getName()
                    + ConsoleColors.prompt("\"? (y/N): "));

            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("yes")) {
                repository.delete(id);
                System.out.println(ConsoleColors.success("✓") + " Deleted task #" + id);
            } else {
                System.out.println(ConsoleColors.warning("Cancelled deletion."));
            }
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.error("Error: ") + "Invalid ID " + idStr
                    + ". ID must be a number.");
        } catch (IllegalArgumentException e) {
            System.out.println(ConsoleColors.error("Error: ") + e.getMessage()
                    + " Use 'show' to see available tasks.");
        }

    }

    private void handleSave() throws IOException {
        service.save();
        System.out.println(ConsoleColors.success("✓") + " Saved tasks to file.");
    }

    private void handleLoad() throws IOException {
        service.load();
        System.out.println(ConsoleColors.success("✓") + " Loaded " + repository.getTaskSize()
                + " tasks from file.");
    }

    private void handleError(Exception e) {
        System.err.println("Error: " + e.getMessage());
    }

    private void handleHelp() {
        System.out.println("Available commands: ");
        System.out.println("  post <text>   Add a new task");
        System.out.println("  done <id>     Mark a task as done");
        System.out.println("  show          Show all tasks");
        System.out.println("  delete <id>   Delete a task");
        System.out.println("  save          Save tasks to file");
        System.out.println("  load          Load tasks from file");
        System.out.println("  help          Show this help message");
        System.out.println("  exit          Quit application");
    }
}


