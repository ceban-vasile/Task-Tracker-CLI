package org.tracker_task_cli.listing_tasks;

import org.tracker_task_cli.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class DateFilter implements TaskFilter{
    private final long fromTimestamp;

    public DateFilter(long fromTimestamp) {
        this.fromTimestamp = fromTimestamp;
    }

    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getCreatedAt().getTime() >= fromTimestamp)
                .collect(Collectors.toList());
    }
}
