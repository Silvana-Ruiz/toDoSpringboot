package com.todo.todosystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cglib.core.Local;
import org.springframework.web.server.ResponseStatusException;

import com.todo.todosystem.exception.ResourceNotFoundException;
import com.todo.todosystem.model.Priority;
import com.todo.todosystem.model.todo;
import com.todo.todosystem.service.todoServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@SpringBootTest
class TodosystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
