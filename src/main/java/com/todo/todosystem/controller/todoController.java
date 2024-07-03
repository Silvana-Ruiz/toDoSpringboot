package com.todo.todosystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todosystem.model.Metrics;
import com.todo.todosystem.model.SearchTodo;
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

    @GetMapping()
    public ResponseEntity<Object> getToDoItems() {
        try {
            List<todo> todoItems = todoServiceInstance.findAll();
            return ResponseEntity.ok(todoItems);
        } catch(Exception ex) {
            return new ResponseEntity<Object>("Failed to fetch to do items", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }


    @GetMapping("filter")
    public ResponseEntity<Object> searchToDos(@RequestBody SearchTodo searchFilter) {
        try {
            List<todo> filteredToDos = todoServiceInstance.searchToDos(searchFilter);
            return new ResponseEntity<Object>(filteredToDos, HttpStatus.ACCEPTED);
        } catch(Exception ex) {
            return new ResponseEntity<Object>("Failed to filter to dos", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // @GetMapping("pagination")
    // public ResponseEntity<Object> getToDoItemsPagination(@RequestParam(defaultValue = "0") int pageNo,
    // @RequestParam(defaultValue = "10") int pageSize) {
    //     try {
    //         Page<todo> todoItems = todoServiceInstance.getAllToDos(pageNo, pageSize);
    //         return ResponseEntity.ok(todoItems);
    //     } catch(Exception ex) {
    //         return new ResponseEntity<Object>("Failed to fetch to do items", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
        
    // }

    // @GetMapping("/api/todo/items/paginated")
    // public ResponseEntity<Page<todo>> getPaginatedTodoItems(
    //         @RequestParam(defaultValue = "0") int page,
    //         @RequestParam(defaultValue = "10") int size) {

    //     Page<todo> todoItems = todoServiceInstance.findPaginated(page, size);
    //     return ResponseEntity.ok(todoItems);
    // }

    @PostMapping()
    public ResponseEntity<Object> createToDoItem(@RequestBody todo toDoItem) {
        try {
            todo createdToDoItem = todoServiceInstance.save(toDoItem);
            return new ResponseEntity<>(createdToDoItem, HttpStatus.CREATED);
        } catch(Exception ex) {
            return new ResponseEntity<Object>("Failed to create to do item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<todo> updateToDoItem(@PathVariable String id, @RequestBody todo toDoItem) {
        todo updatedToDo = todoServiceInstance.update(id, toDoItem);
        return new ResponseEntity<>(updatedToDo, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToDoItem(@PathVariable String id) {
        String deletionMessage = todoServiceInstance.delete(id);
        return new ResponseEntity<>(deletionMessage, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<Object> markDoneToDoItem(@PathVariable String id) {
        try {
            Metrics metrics = todoServiceInstance.setDone(id);
            return new ResponseEntity<>(metrics, HttpStatus.ACCEPTED);
        } catch(Exception ex) {
            System.out.println("exception" + ex);
            return new ResponseEntity<Object>("To do was not found", HttpStatus.NOT_FOUND);
        }
        
    }

    @PutMapping("/{id}/undone")
    public ResponseEntity<Object> markUndoneToDoItem(@PathVariable String id) {
        try {
            Metrics metrics = todoServiceInstance.setUndone(id);
            return new ResponseEntity<>(metrics, HttpStatus.ACCEPTED);
        } catch(Exception ex) {
            return new ResponseEntity<Object>("To do was not found", HttpStatus.NOT_FOUND);
        }
    }

}
