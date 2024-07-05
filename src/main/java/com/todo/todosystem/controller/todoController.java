package com.todo.todosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todosystem.model.Metrics;
import com.todo.todosystem.model.SearchPriority;
import com.todo.todosystem.model.SearchState;
import com.todo.todosystem.model.todo;
import com.todo.todosystem.repository.todoRepository;
import com.todo.todosystem.service.todoServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/todo")
public class todoController {
    @Autowired
    private todoServiceImpl todoServiceInstance;
    


    public todoController(todoServiceImpl todoServiceInstance) {
        this.todoServiceInstance = todoServiceInstance;
    }

    // @GetMapping()
    // public ResponseEntity<Object> getToDoItems() {
    //     try {
    //         List<todo> todoItems = todoServiceInstance.getTodos();
    //         return ResponseEntity.ok(todoItems);
    //     } catch(Exception ex) {
    //         return new ResponseEntity<Object>("Failed to fetch to do items", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
        
    // }


    @GetMapping()
    public ResponseEntity<Object> searchToDos(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "priority", defaultValue = "All") SearchPriority priority,
            @RequestParam(value = "state", defaultValue = "All") SearchState state
        ) {
        try {
            List<todo> filteredToDos = todoServiceInstance.getFilteredToDos(text, priority, state);
            return new ResponseEntity<Object>(filteredToDos, HttpStatus.ACCEPTED);
        } catch(Exception ex) {
            return new ResponseEntity<Object>("Failed to filter to dos", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getToDoById(@PathVariable String id) {
        try {
            todo toDo = todoServiceInstance.getToDoById(id);
            return new ResponseEntity<Object>(toDo, HttpStatus.ACCEPTED);
        } catch(Exception ex) {
            System.out.println("exception " + ex);
            return new ResponseEntity<Object>("Failed to get to do", HttpStatus.INTERNAL_SERVER_ERROR);
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
            todo createdToDoItem = todoServiceInstance.saveTodo(toDoItem);
            return new ResponseEntity<>(createdToDoItem, HttpStatus.CREATED);
        } catch(Exception ex) {
            return new ResponseEntity<Object>("Failed to create to do item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateToDoItem(@PathVariable String id, @RequestBody todo toDoItem) {
        List<todo>  updatedToDo = todoServiceInstance.updateToDo(id, toDoItem);
        return new ResponseEntity<>(updatedToDo, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteToDoItem(@PathVariable String id) {
        List<todo> updatedToDoList = todoServiceInstance.deleteToDo(id);
        return new ResponseEntity<>(updatedToDoList, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<Object> markDoneToDoItem(@PathVariable String id) {
        try {
            Metrics metrics = todoServiceInstance.setDoneToDo(id);
            return new ResponseEntity<>(metrics, HttpStatus.ACCEPTED);
        } catch(Exception ex) {
            System.out.println("exception" + ex);
            return new ResponseEntity<Object>("To do was not found", HttpStatus.NOT_FOUND);
        }
        
    }

    @PutMapping("/{id}/undone")
    public ResponseEntity<Object> markUndoneToDoItem(@PathVariable String id) {
        try {
            Metrics metrics = todoServiceInstance.setUndoneToDo(id);
            return new ResponseEntity<>(metrics, HttpStatus.ACCEPTED);
        } catch(Exception ex) {
            return new ResponseEntity<Object>("To do was not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<Object> getPaginatedToDos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
        ) {
            Map<String, Object>  mapPaginatedToDos  = todoServiceInstance.getPaginatedToDo(page, size);
            return new ResponseEntity<>(mapPaginatedToDos, HttpStatus.ACCEPTED);
       
    }


}
