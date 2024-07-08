// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.cglib.core.Local;
// import org.springframework.web.server.ResponseStatusException;

// import com.todo.todosystem.model.Priority;
// import com.todo.todosystem.model.todo;

// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.when;

// @ExtendWith(MockitoExtension.class)
// public class ToDoServiceTests {

//     @Mock
//     private List<todo> mockToDoList;

//     @InjectMocks
//     private todoServiceImpl toDoService;

//     private todo toDo1;
//     private todo toDo2;

//     @BeforeEach
//     public void setup() {
//         todo todo1 = new todo("Task 1", Optional.of(LocalDate.now()), Optional.empty(), Priority.Low);
//         todo todo2 = new todo("Task 2", Optional.empty(), Optional.of(LocalDateTime.now()), Priority.High);

//         List<todo> toDoList = new ArrayList<>();
//         toDoList.add(todo1);
//         toDoList.add(todo2);

//         when(mockToDoList.stream()).thenReturn(toDoList.stream());
        
//     }

//     @Test
//     public void testGetToDoById_ValidId() {
//         String id = "1";

//         todo result = toDoService.getToDoById(id);

//         assertEquals("Task 1", result.getText());
//         assertEquals(Priority.Low, result.getPriority());
//     }
// }
