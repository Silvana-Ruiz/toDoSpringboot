package com.todo.todosystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todosystem.service.todoService;

@RestController
public class todoController {
    @Autowired
    private todoService todoServiceInstance;
    
    @RequestMapping("/hello1")
    public String hello() {
        return "Hello world!!!";
    }

    
    @RequestMapping("/todo")
    public List<String> getNewToDo() {
        return todoServiceInstance.createToDo();
    }
}
