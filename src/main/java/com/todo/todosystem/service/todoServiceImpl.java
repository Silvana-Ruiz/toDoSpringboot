package com.todo.todosystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.todosystem.model.Metrics;
import com.todo.todosystem.model.SearchPriority;
import com.todo.todosystem.model.SearchState;
import com.todo.todosystem.model.todo;
import com.todo.todosystem.repository.todoRepository;




@Service
public class todoServiceImpl {
    @Autowired
    private todoRepository repository;

    
    public todoServiceImpl(todoRepository repository) {
        this.repository = repository;
    }

   
    // public Page<todo> getAllToDos(int pageNo, int pageSize) { 
    //     PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
    //     return repository.findAll(pageRequest);
      
    // }

    public todo getToDoById(String id) {
        return repository.getTodo(id);
    }

    public List<todo> findAll() {
        return repository.getTodos();
    }

    public List<todo> searchToDos(String text, SearchPriority priority, SearchState state) {
        return repository.getFilteredToDos(text, priority, state);
    }

    // public Page<todo> findPaginated(int page, int size) {
      
    //     Pageable pageable = PageRequest.of(page, size);
    //     return repository.findAll(pageable);
    // }

    public todo save(todo todoItem) {
        return repository.saveTodo(todoItem);
    }

    public List<todo> update(String id, todo todoItem) {
        return repository.update(id, todoItem);
    }
    public List<todo> delete(String id) {
        return repository.deleteToDo(id);
    }

    public Metrics setDone(String id) {
        return repository.setDoneToDo(id);
    }
    public Metrics setUndone(String id) {
        return repository.setUndoneToDo(id);
    }
}
