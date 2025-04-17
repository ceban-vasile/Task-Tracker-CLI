package org.tracker_task_cli.listing_tasks;

import org.tracker_task_cli.model.Status;
import org.tracker_task_cli.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class StatusFilter implements TaskFilter {
    private final Status statusToFilter;

    public StatusFilter(Status statusToFilter) {
        this.statusToFilter = statusToFilter;
    }

    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getStatus() == statusToFilter)
                .collect(Collectors.toList());
    }
}
