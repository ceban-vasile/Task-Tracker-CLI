package org.tracker_task_cli.file_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.tracker_task_cli.model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static FileHandler fileInstance;
    private static final String DATA_FILE = "tasks.json";
    private final ObjectMapper mapper;

    private FileHandler() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static FileHandler getInstance() {
        if (fileInstance == null) {
            fileInstance = new FileHandler();
        }
        return fileInstance;
    }

    public void write_Task(Task task) {
        try {
            File file = new File("data/" + DATA_FILE);
            List<Task> tasks;

            if (file.exists() && file.length() > 0) {
                tasks = mapper.readValue(file,
                        mapper.getTypeFactory().constructCollectionType(List.class, Task.class));

                boolean taskExists = false;
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getId() == task.getId()) {
                        tasks.set(i, task);
                        taskExists = true;
                        break;
                    }
                }

                if (!taskExists) {
                    tasks.add(task);
                }
            } else {
                tasks = new ArrayList<>();
                tasks.add(task);
            }
            mapper.writeValue(file, tasks);
            System.out.println("Task saved successfully!");

        } catch (IOException e) {
            System.err.println("Error writing task to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Task> readAllTasks() {
        try {
            File file = new File("data/" + DATA_FILE);
            if (file.exists() && file.length() > 0) {
                return mapper.readValue(file,
                        mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
            }
        } catch (IOException e) {
            System.err.println("Error reading tasks from file: " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean deleteTask(int id) {
        try {
            File file = new File("data/" + DATA_FILE);
            if (file.exists() && file.length() > 0) {
                List<Task> tasks = mapper.readValue(file,
                        mapper.getTypeFactory().constructCollectionType(List.class, Task.class));

                boolean removed = tasks.removeIf(task -> task.getId() == id);
                if (removed) {
                    mapper.writeValue(file, tasks);
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error deleting task: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Task getTaskById(int id) {
        List<Task> tasks = readAllTasks();
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }
}