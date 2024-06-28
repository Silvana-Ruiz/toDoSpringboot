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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/todo")
public class todoController {
    
    private todoServiceImpl todoServiceInstance;


    public todoController(todoServiceImpl todoServiceInstance) {
        this.todoServiceInstance = todoServiceInstance;
    }
    
    // @RequestMapping("/hello")
    // public String hello() {
    //     return "Hello world!!!";
    // }

    

    @GetMapping()
    public ResponseEntity<List<todo>> getToDoItems() {
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

    @PostMapping()
    public ResponseEntity<todo> createToDoItem(@RequestBody todo toDoItem) {
        todo createdToDoItem = todoServiceInstance.save(toDoItem);
        return new ResponseEntity<>(createdToDoItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<todo> updateToDoItem(@PathVariable String id, @RequestBody todo toDoItem) {
        todo updatedToDo = todoServiceInstance.update(id, toDoItem);
        return new ResponseEntity<>(updatedToDo, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<todo> markDoneToDoItem(@PathVariable String id) {
        todo updatedToDo = todoServiceInstance.setDone(id);
        return new ResponseEntity<>(updatedToDo, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/undone")
    public ResponseEntity<todo> markUndoneToDoItem(@PathVariable String id) {
        todo updatedToDo = todoServiceInstance.setUndone(id);
        return new ResponseEntity<>(updatedToDo, HttpStatus.ACCEPTED);
    }

}
