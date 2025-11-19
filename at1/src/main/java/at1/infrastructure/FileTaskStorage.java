package at1.infrastructure;

import at1.domain.Task;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileTaskStorage {
    private static final String FILE_NAME = "todo.txt";

    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(FILE_NAME);

        // 親ディレクトリが存在しない場合は作成
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        // ファイルが存在しない場合は作成
        if (!file.exists()) {
            file.createNewFile();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.println(serializeTask(task));
            }
        }
    }

    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = deserializeTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }

    private String serializeTask(Task task) {
        return task.getId() + "," + task.getName() + "," + task.isDone();
    }

    private Task deserializeTask(String line) {
        try {
            String[] parts = line.split(",", 3);
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            boolean isDone = Boolean.parseBoolean(parts[2]);

            Task task = new Task(id, name);
            task.setDone(isDone);
            return task;
        } catch (Exception e) {
            return null;
        }
    }
}
