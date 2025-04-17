package org.tracker_task_cli.command;

import org.tracker_task_cli.file_management.FileHandler;
import org.tracker_task_cli.model.Task;

public class DeleteTaskCommand implements Command {
    private final int task_id;
    private Task deleteTask;
    private final FileHandler fileHandler;

    public DeleteTaskCommand(int task_id) {
        this.task_id = task_id;
        fileHandler = FileHandler.getInstance();
    }


    @Override
    public void execute() {
        deleteTask = fileHandler.getTaskById(task_id);
        fileHandler.deleteTask(task_id);
    }

    @Override
    public void undo() {
        if (deleteTask != null) {
            fileHandler.write_Task(deleteTask);
        }
    }
}
