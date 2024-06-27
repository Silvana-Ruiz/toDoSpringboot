package com.todo.todosystem.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.parser.Priority;
import org.springframework.stereotype.Repository;

import com.todo.todosystem.model.todo;


@Repository
public class todoRepositoryImpl implements todoRepository{

    private List<todo> toDoList = new ArrayList<todo>();
    @Override
    public List<todo> saveTodo(todo newTodo) {
        toDoList.add(newTodo);
        return toDoList;
    }

    @Override
    public List<todo> getTodos() {
        return toDoList;
    }
    

    

    // public void createTodos() {

    //     // todo todo1 = new todo(1, "Assignment 1", LocalDate.parse("2020-01-08"), LocalDate.parse("2020-01-08"), com.todo.todosystem.model.Priority.High);
    //     todo todo1 = new todo(1, "Assignment 1");
    //     todo todo2 = new todo(2, "Assignment 2");
    //     toDoList = List.of(
    //         todo1  , todo2 
           
    //     );
    // }

    // public List<todo> findAll() {
    //     return toDoList;
    // }


}
