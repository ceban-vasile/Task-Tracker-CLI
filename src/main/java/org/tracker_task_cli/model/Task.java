package org.tracker_task_cli.model;

import java.util.Date;

public class Task {
    private int id;
    private String description;
    private Status status;
    private Date createdAt;
    private Date updatedAt;

    public Task() {
    }

    private Task(int id, String description, Status status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class Builder {
        private int id;
        private String description;
        private Status status;
        private Date createdAt;
        private Date updatedAt;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Task build() {
            return new Task(id, description, status, createdAt, updatedAt);
        }
    }

    public String toString() {
        return "Task:\n id=" + id + ",\n description=" + description + ",\n status=" + status + ",\n createdAt=" + createdAt + ",\n updatedAt=" + updatedAt + "\n";
    }
}