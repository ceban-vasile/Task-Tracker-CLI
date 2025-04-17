package org.tracker_task_cli;

import org.tracker_task_cli.command.*;
import org.tracker_task_cli.create_task.*;
import org.tracker_task_cli.model.Status;
import org.tracker_task_cli.model.Task;
import org.tracker_task_cli.listing_tasks.*;
import org.tracker_task_cli.file_management.FileHandler;

import java.util.List;

public class Main {
    private static final CommandInvoker invoker = new CommandInvoker();

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String command = args[0].toLowerCase();

        try {
            switch (command) {
                case "add":
                    handleAddCommand(args);
                    break;
                case "list":
                    handleListCommand(args);
                    break;
                case "delete":
                    handleDeleteCommand(args);
                    break;
                case "update":
                    handleUpdateCommand(args);
                    break;
                case "help":
                    printUsage();
                    break;
                default:
                    System.err.println("Unknown command: " + command);
                    printUsage();
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            printUsage();
        }
    }

    private static void handleAddCommand(String[] args) {
        if (args.length < 2) {
            System.err.println("Missing task description");
            return;
        }

        String description = args[1];
        Status status = Status.TODO;

        if (args.length > 2 && args[2].startsWith("--status=")) {
            String statusStr = args[2].substring("--status=".length()).toUpperCase();
            try {
                status = Status.valueOf(statusStr);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid status: " + statusStr);
                return;
            }
        }

        TaskFactory factory;
        switch (status) {
            case IN_PROGRESS:
                factory = new InProgressTask(description);
                break;
            case DONE:
                factory = new DoneTask(description);
                break;
            default:
                factory = new TodoTask(description);
                break;
        }

        Task task = factory.createTask();
        Command addCommand = new AddTaskCommand(task);
        invoker.executeCommand(addCommand);

        System.out.println("Task added with ID: " + task.getId());
    }

    private static void handleListCommand(String[] args) {
        TaskFilterContext filterContext = new TaskFilterContext();
        TaskFilter strategy = null;

        if (args.length > 1) {
            String option = args[1];
            if (option.startsWith("--status=")) {
                String statusStr = option.substring("--status=".length()).toUpperCase();
                try {
                    Status status = Status.valueOf(statusStr);
                    strategy = new StatusFilter(status);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid status: " + statusStr);
                    return;
                }
            } else if (option.startsWith("--after=")) {
                try {
                    long timestamp = Long.parseLong(option.substring("--after=".length()));
                    strategy = new DateFilter(timestamp);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid timestamp: " + option);
                    return;
                }
            }
        }

        if (strategy == null) {
            strategy = tasks -> tasks;
        }

        filterContext.setStrategy(strategy);
        List<Task> filteredTasks = filterContext.executeFilter();

        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("ID\tSTATUS\t\tCREATED\t\t\tDESCRIPTION");
            System.out.println("----------------------------------------------------------");
            for (Task task : filteredTasks) {
                System.out.printf("%d\t%s\t%s\t%s%n",
                        task.getId(),
                        task.getStatus(),
                        task.getCreatedAt(),
                        task.getDescription());
            }
        }
    }

    private static void handleDeleteCommand(String[] args) {
        if (args.length < 2) {
            System.err.println("Missing task ID");
            return;
        }

        try {
            int taskId = Integer.parseInt(args[1]);
            Command deleteCommand = new DeleteTaskCommand(taskId);
            invoker.executeCommand(deleteCommand);
            System.out.println("Task " + taskId + " deleted successfully");
        } catch (NumberFormatException e) {
            System.err.println("Invalid task ID: " + args[1]);
        }
    }

    private static void handleUpdateCommand(String[] args) {
        if (args.length < 3) {
            System.err.println("Missing parameters for update");
            return;
        }

        try {
            int taskId = Integer.parseInt(args[1]);

            Task existingTask = FileHandler.getInstance().getTaskById(taskId);
            if (existingTask == null) {
                System.err.println("Task not found: " + taskId);
                return;
            }

            String description = existingTask.getDescription();
            Status status = existingTask.getStatus();

            for (int i = 2; i < args.length; i++) {
                String arg = args[i];
                if (arg.startsWith("--desc=")) {
                    description = arg.substring("--desc=".length());
                } else if (arg.startsWith("--status=")) {
                    String statusStr = arg.substring("--status=".length()).toUpperCase();
                    try {
                        status = Status.valueOf(statusStr);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid status: " + statusStr);
                        return;
                    }
                }
            }

            Task updatedTask = new Task.Builder()
                    .setId(taskId)
                    .setDescription(description)
                    .setStatus(status)
                    .setCreatedAt(existingTask.getCreatedAt())
                    .setUpdatedAt(new java.util.Date())
                    .build();

            Command updateCommand = new UpdateTaskCommand(updatedTask);
            invoker.executeCommand(updateCommand);

            System.out.println("Task " + taskId + " updated successfully");

        } catch (NumberFormatException e) {
            System.err.println("Invalid task ID: " + args[1]);
        }
    }

    private static void printUsage() {
        System.out.println("Task Tracker CLI - Usage:");
        System.out.println("  add <description> [--status=TODO|IN_PROGRESS|COMPLETED]");
        System.out.println("  list [--status=TODO|IN_PROGRESS|COMPLETED] [--after=timestamp]");
        System.out.println("  delete <task_id>");
        System.out.println("  update <task_id> [--desc=new_description] [--status=TODO|IN_PROGRESS|COMPLETED]");
        System.out.println("  help");
    }
}