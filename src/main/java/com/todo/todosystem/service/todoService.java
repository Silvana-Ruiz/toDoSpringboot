package com.todo.todosystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.todo.todosystem.model.todo;

public interface todoService {
    // public List<todo> getToDos();
    // public Page<todo> getToDos(int pageNo, int pageSize);
    public List<todo> findAll();
    public Page<todo> findPaginated(int page, int size);
    public todo save(todo todoItem);
}
