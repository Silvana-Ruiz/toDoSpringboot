package com.todo.todosystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.todosystem.model.todo;


public interface todoRepository extends JpaRepository<todo, Integer> {
    List<todo> getToDos();
    List<String> createToDos();
}
