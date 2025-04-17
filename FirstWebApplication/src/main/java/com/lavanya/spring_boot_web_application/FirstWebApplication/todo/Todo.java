package com.lavanya.spring_boot_web_application.FirstWebApplication.todo;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    private String UserName;

    @NotEmpty(message = "*Mandatory field")
    private String description;
    private LocalDate TargetDate;
    private boolean done;

    public Todo(int id, String userName, String description, LocalDate targetDate, boolean done) {
        this.id = id;
        UserName = userName;
        this.description = description;
        TargetDate = targetDate;
        this.done = done;
    }

    public Todo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return TargetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        TargetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", UserName='" + UserName + '\'' +
                ", description='" + description + '\'' +
                ", TargetDate=" + TargetDate +
                ", done=" + done +
                '}';
    }
}
