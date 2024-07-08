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
}
