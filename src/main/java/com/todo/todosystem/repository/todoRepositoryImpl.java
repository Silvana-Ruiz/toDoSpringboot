package com.todo.todosystem.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.todo.todosystem.model.todo;


@Repository
public class todoRepositoryImpl implements todoRepository{

    private List<todo> toDoList = new ArrayList<todo>();
    @Override
    public todo saveTodo(todo newToDo) {
        // Validations
        int textLength = newToDo.getText().length();
        if (textLength == 0 ) {
            throw new IllegalArgumentException("Please add a to do description");
        } else if (textLength > 120) {
            throw new IllegalArgumentException("The to do description should be at most 120 characters");
        }

        // Add Id
        newToDo.setId(UUID.randomUUID().toString()); 
        toDoList.add(newToDo);
        return newToDo;
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
