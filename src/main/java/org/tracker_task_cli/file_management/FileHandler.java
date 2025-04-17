package org.tracker_task_cli.file_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.tracker_task_cli.model.Task;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    private static FileHandler fileInstance;

    private FileHandler() {

    }

    public static FileHandler getInstance() {
        if (fileInstance == null) {
            fileInstance = new FileHandler();
        }

        return fileInstance;
    }

    public void write_Task(Task task) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File("task.json"), task);
            System.out.println("Task written to task.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
