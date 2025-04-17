package org.tracker_task_cli.command;

public interface Command {
    void execute();
    void undo();
}
