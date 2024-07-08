package com.todo.todosystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Id. This could be a number or string or a combination. Must be unique. 
// Text (required). Max length is 120 chars. 
// A due date (optional). 
// Done/undone flag 
// A done date. When the “to do” is marked as done this date is set 
// Priority (required). Options: High, Medium and Low. 
// Creation date. 

@Entity
public class todo {
    
    @Id
    private String id;
    private String text;
    private Optional<LocalDate> dueDate;
    private boolean doneFlag;
    private Optional<LocalDateTime> doneDate;
    private Priority priority;
    private LocalDateTime creationDate;

    
    public todo(String text, Optional<LocalDate> dueDate, Optional<LocalDateTime> doneDate, Priority priority) {
        this.id = "";
        this.text = text;
        this.dueDate = dueDate;
        this.doneFlag = false;
        this.doneDate = doneDate;
        this.priority = priority;
        this.creationDate = LocalDateTime.now();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String Id) {
        this.id = UUID.randomUUID().toString();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Optional<LocalDate> getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Optional<LocalDate> dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDoneFlag() {
        return this.doneFlag;
    }

    public boolean getDoneFlag() {
        return this.doneFlag;
    }

    public void setDoneFlag(boolean doneFlag) {
        this.doneFlag = doneFlag;
    }

    public Optional<LocalDateTime> getDoneDate() {
        return this.doneDate;
    }

    public void setDoneDate(Optional<LocalDateTime> doneDate) {
        this.doneDate = doneDate;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}


    

