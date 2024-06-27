package com.todo.todosystem.model;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String text;
    private Optional<LocalDate> dueDate;
    private boolean doneFlag;
    private Optional<LocalDate> doneDate;
    private Priority priority;
    private LocalDate creationDate;

    // remove the optional in priority parameter
    public todo(int Id, String text) {
        this.Id = Id;
        this.text = text;
        // this.dueDate = dueDate;
        this.doneFlag = false;
        // this.doneDate = doneDate;
        // this.priority = priority;
        this.creationDate = LocalDate.now();
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public Optional<LocalDate> getDoneDate() {
        return this.doneDate;
    }

    public void setDoneDate(Optional<LocalDate> doneDate) {
        this.doneDate = doneDate;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}


    

