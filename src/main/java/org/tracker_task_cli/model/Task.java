package org.tracker_task_cli.model;

import java.util.Date;

public class Task {
    private final int id;
    private final String description;
    private final Status status;
    private final Date createdAt, updatedAt;

    private Task(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }
     public static class Builder{
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

         public Task build(){
             return new Task(this);
         }
     }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
