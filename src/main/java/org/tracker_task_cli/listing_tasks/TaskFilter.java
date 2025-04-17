package org.tracker_task_cli.listing_tasks;

import org.tracker_task_cli.model.Task;

import java.util.List;

public interface TaskFilter {
    List<Task> filter(List<Task> tasks);
}
