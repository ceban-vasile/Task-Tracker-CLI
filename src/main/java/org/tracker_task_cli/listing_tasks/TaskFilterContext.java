package org.tracker_task_cli.listing_tasks;

import org.tracker_task_cli.file_management.FileHandler;
import org.tracker_task_cli.model.Task;

import java.util.List;

public class TaskFilterContext {
    private TaskFilter strategy;
    private final FileHandler fileHandler;

    public TaskFilterContext() {
        this.fileHandler = FileHandler.getInstance();
    }

    public void setStrategy(TaskFilter strategy) {
        this.strategy = strategy;
    }

    public List<Task> executeFilter() {
        List<Task> allTasks = fileHandler.readAllTasks();

        return strategy.filter(allTasks);
    }


}
