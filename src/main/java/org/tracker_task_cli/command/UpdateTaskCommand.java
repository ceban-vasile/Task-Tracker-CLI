package org.tracker_task_cli.command;

import org.tracker_task_cli.file_management.FileHandler;
import org.tracker_task_cli.model.Task;

public class UpdateTaskCommand implements Command {
    private final Task updatedTask;
    private Task originalTask;
    private final FileHandler fileHandler;

    public UpdateTaskCommand(Task updatedTask) {
        this.updatedTask = updatedTask;
        this.fileHandler = FileHandler.getInstance();
    }

    @Override
    public void execute() {
        // Store original for undo capability
        originalTask = fileHandler.getTaskById(updatedTask.getId());
        fileHandler.write_Task(updatedTask);
    }

    @Override
    public void undo() {
        if (originalTask != null) {
            fileHandler.write_Task(originalTask);
        }
    }
}