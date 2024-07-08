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


    private List<todo> toDoList;
    @BeforeEach
    public void setup() {
        toDoList = new ArrayList<>();
        todo todo1 = new todo("Task 1", Optional.empty(), Optional.empty(), Priority.Low);
        todo todo2 = new todo("Task 2", Optional.empty(), Optional.empty(), Priority.Medium);
        todo todo3 = new todo("Task 3", Optional.empty(), Optional.empty(), Priority.High);
        toDoList.add(todo1);
        toDoList.add(todo2);
        toDoList.add(todo3);
        
    }

    @Test
    public void testDeleteToDo_Successful() {
        // Get the ID of the first todo item
        String idToDelete = toDoList.get(0).getId();

        // Call the deleteToDo method
        List<todo> remainingTodos = deleteToDoHelper(idToDelete);

        // Verify that the todo item with the specified ID is deleted
        assertEquals(2, remainingTodos.size());
        assertFalse(remainingTodos.stream().anyMatch(todo -> todo.getId().equals(idToDelete)));
    }


	private List<todo> deleteToDoHelper(String id) {

        Iterator<todo> iterator = toDoList.iterator();
        while (iterator.hasNext()) {
            todo todo = iterator.next();
            if (todo.getId().equals(id)) {
                iterator.remove();
                return toDoList;
            }
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }

}
