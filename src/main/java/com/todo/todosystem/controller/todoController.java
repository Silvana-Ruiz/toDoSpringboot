package com.todo.todosystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todosystem.model.todo;
import com.todo.todosystem.service.todoServiceImpl;

@RestController
public class todoController {
    
    private todoServiceImpl todoServiceInstance;


    public todoController(todoServiceImpl todoServiceInstance) {
        this.todoServiceInstance = todoServiceInstance;
    }
    
    @RequestMapping("/hello")
    public String hello() {
        return "Hello world!!!";
    }

    

    @GetMapping("/api/todo/items")
    public ResponseEntity<List<todo>> getTodoItems() {
        List<todo> todoItems = todoServiceInstance.findAll();
        return ResponseEntity.ok(todoItems);
    }

    // @GetMapping("/api/todo/items/paginated")
    // public ResponseEntity<Page<todo>> getPaginatedTodoItems(
    //         @RequestParam(defaultValue = "0") int page,
    //         @RequestParam(defaultValue = "10") int size) {

    //     Page<todo> todoItems = todoServiceInstance.findPaginated(page, size);
    //     return ResponseEntity.ok(todoItems);
    // }

    @PostMapping("/create")
    public ResponseEntity<List<todo>> createTodoItem(@RequestBody todo todoItem) {
        List<todo> createdTodoItem = todoServiceInstance.save(todoItem);
        return new ResponseEntity<>(createdTodoItem, HttpStatus.CREATED);
    }
}
