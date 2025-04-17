package org.tracker_task_cli;

import org.tracker_task_cli.command.AddTaskCommand;
import org.tracker_task_cli.command.Command;
import org.tracker_task_cli.command.CommandInvoker;
import org.tracker_task_cli.model.Status;
import org.tracker_task_cli.model.Task;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Task task = new Task.Builder()
                .setId(3)
                .setDescription("This is a task3")
                .setStatus(Status.IN_PROGRESS)
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date())
                .build();
        CommandInvoker invoker = new CommandInvoker();
        Command addTask = new AddTaskCommand(task);
        invoker.executeCommand(addTask);

    }
}