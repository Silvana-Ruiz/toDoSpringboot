package com.todo.todosystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.todo.todosystem.repository.todoRepositoryImpl;

@Service
public class todoServiceImpl implements todoService {
    @Autowired
    private todoRepositoryImpl repository;
    // @Override
    // public List<todo> getToDos() {
        
    // }
    @Override
    public List<String> createToDo() { 
        return repository.createToDos();
    }
}
