package com.todo.todosystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.todosystem.model.SearchTodo;
import com.todo.todosystem.model.todo;



public interface todoRepository {
    todo saveTodo(todo newTodo);
    List<todo> getTodos();
    todo update(String id, todo updatedToDo);
    todo setDoneToDo(String id);
    todo setUndoneToDo(String id);
    String deleteToDo(String id);
    List<todo> getFilteredToDos(SearchTodo searchTodoItem);
}
