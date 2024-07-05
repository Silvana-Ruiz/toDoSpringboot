package com.todo.todosystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.todo.todosystem.model.Metrics;
import com.todo.todosystem.model.Priority;
import com.todo.todosystem.model.SearchPriority;
import com.todo.todosystem.model.SearchState;
import com.todo.todosystem.model.todo;

@Repository
public interface todoRepository extends JpaRepository<todo, String> {
    // todo getTodo(String id);
    // todo saveTodo(todo newTodo);
    // List<todo> getTodos();
    // List<todo> update(String id, todo updatedToDo);
    // Metrics setDoneToDo(String id);
    // Metrics setUndoneToDo(String id);
    // List<todo> deleteToDo(String id);
    // List<todo> getFilteredToDos(String text, SearchPriority priority, SearchState state);
    // // Page<todo> getPaginatedToDos(int pageNo, int pageSize);
    // // Page<todo> findAll(Pageable pageable);
    // void validateText(String text);
    // Metrics calculateAverageTime(LocalDateTime creationDate, LocalDateTime doneDate, Priority priority);
    // Metrics removeFromAverageTime(LocalDateTime creationDate, LocalDateTime doneDate, Priority priority);
}
