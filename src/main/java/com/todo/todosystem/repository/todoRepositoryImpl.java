package com.todo.todosystem.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.todo.todosystem.model.todo;

@Repository
public class todoRepositoryImpl {
    private List<todo> toDos;
    private List<String> testList = new ArrayList<String>();
  
    public List<todo> getToDos() {
        return toDos;
    }

    
    public List<String> createToDos() {
        
        testList.add("hola");
        return testList;
    }
}
