package com.todo.todosystem.model;


public class SearchTodo {
    private String text;
    private SearchPriority priority;
    private SearchState state;

    public SearchTodo(String text, SearchPriority priority, SearchState state) {
        this.text = text;
        this.priority = priority;
        this.state = state;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SearchPriority getPriority() {
        return this.priority;
    }

    public void setPriority(SearchPriority priority) {
        this.priority = priority;
    }

    public SearchState getState() {
        return this.state;
    }

    public void setState(SearchState state) {
        this.state = state;
    }




}
