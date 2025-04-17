package org.tracker_task_cli;

import org.tracker_task_cli.file_management.FileHandler;
import org.tracker_task_cli.model.Status;
import org.tracker_task_cli.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Task task = new Task.Builder()
                .setId(2)
                .setDescription("This is a task2")
                .setStatus(Status.IN_PROGRESS)
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date())
                .build();
        FileHandler fileHandler = FileHandler.getInstance();
        fileHandler.write_Task(task);
        List<Task> s = fileHandler.readAllTasks();
        for (Task t : s) {
            System.out.println(t);
        }

    }
}