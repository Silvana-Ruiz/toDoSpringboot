package com.todo.todosystem.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.todo.todosystem.model.Metrics;
import com.todo.todosystem.model.Priority;
import com.todo.todosystem.model.SearchTodo;
import com.todo.todosystem.model.todo;


public interface todoRepository {
    todo saveTodo(todo newTodo);
    List<todo> getTodos();
    todo update(String id, todo updatedToDo);
    Metrics setDoneToDo(String id);
    Metrics setUndoneToDo(String id);
    String deleteToDo(String id);
    List<todo> getFilteredToDos(SearchTodo searchTodoItem);
    // Page<todo> getPaginatedToDos(int pageNo, int pageSize);
    // Page<todo> findAll(Pageable pageable);
    void validateText(String text);
    Metrics calculateAverageTime(LocalDateTime creationDate, LocalDateTime doneDate, Priority priority);
    Metrics removeFromAverageTime(LocalDateTime creationDate, LocalDateTime doneDate, Priority priority);
}
