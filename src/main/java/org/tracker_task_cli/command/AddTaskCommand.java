package org.tracker_task_cli.command;

import org.tracker_task_cli.file_management.FileHandler;
import org.tracker_task_cli.model.Task;

public class AddTaskCommand implements Command{

    private final Task addTask;
    private final FileHandler fileHandler;

    public AddTaskCommand(Task addTask) {
        this.addTask = addTask;
        this.fileHandler = FileHandler.getInstance();
    }

    @Override
    public void execute() {
        fileHandler.write_Task(addTask);
    }

    @Override
    public void undo() {
        fileHandler.deleteTask(addTask.getId());
    }
}
