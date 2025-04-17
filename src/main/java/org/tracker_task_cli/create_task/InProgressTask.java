package org.tracker_task_cli.create_task;

import org.tracker_task_cli.model.Status;
import org.tracker_task_cli.model.Task;

import java.util.Date;

public class InProgressTask implements TaskFactory{
    private final String description;

    public InProgressTask(String description) {
        this.description = description;
    }

    @Override
    public Task createTask() {
        return new Task.Builder()
                .setId(generateId())
                .setDescription(description)
                .setStatus(Status.IN_PROGRESS)
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date())
                .build();
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}
