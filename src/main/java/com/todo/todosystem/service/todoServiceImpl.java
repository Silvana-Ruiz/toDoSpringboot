package com.todo.todosystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.todosystem.model.todo;
import com.todo.todosystem.repository.todoRepository;




@Service
public class todoServiceImpl {
    @Autowired
    private todoRepository repository;

    
    public todoServiceImpl(todoRepository repository) {
        this.repository = repository;
    }

   
    // @Override
    // public Page<todo> getToDos(int pageNo, int pageSize) { 
    //     Pageable pageable = PageRequest.of(pageNo, pageSize);
    //     return repository.findAll(pageable).getContent();
    // }


    public List<todo> findAll() {
        return repository.getTodos();
    }

    // public Page<todo> findPaginated(int page, int size) {
      
    //     Pageable pageable = PageRequest.of(page, size);
    //     return repository.findAll(pageable);
    // }

    public todo save(todo todoItem) {
        return repository.saveTodo(todoItem);
    }

    public todo update(String id, todo todoItem) {
        return repository.update(id, todoItem);
    }

    public todo setDone(String id) {
        return repository.setDoneToDo(id);
    }
    public todo setUndone(String id) {
        return repository.setUndoneToDo(id);
    }
}
