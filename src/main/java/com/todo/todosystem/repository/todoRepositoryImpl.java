package com.todo.todosystem.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.todo.todosystem.model.Priority;
import com.todo.todosystem.model.SearchPriority;
import com.todo.todosystem.model.SearchState;
import com.todo.todosystem.model.SearchTodo;
import com.todo.todosystem.model.todo;
import com.todo.todosystem.exception.ResourceNotFoundException;


@Repository
public class todoRepositoryImpl implements todoRepository {

    private List<todo> toDoList = new ArrayList<todo>();
    @Override
    public todo saveTodo(todo newToDo) {
        // Validations
        validateText(newToDo.getText());
        // Add Id
        newToDo.setId(UUID.randomUUID().toString()); 
        toDoList.add(newToDo);
        return newToDo;
    }

    @Override
    public List<todo> getTodos() {
        return toDoList;
    }

    @Override
    public List<todo> getFilteredToDos(SearchTodo searchTodoItem) {
        List<todo> filteredToDos = new ArrayList<todo>();
        List<todo> temporaryToDos = new ArrayList<todo>();

        String searchString = searchTodoItem.getText();
        SearchPriority searchPriority = searchTodoItem.getPriority();
        SearchState searchState = searchTodoItem.getState();

        boolean showAllPriorities = searchPriority == SearchPriority.All;
        boolean showAllStates = searchState == SearchState.All;
        
        // Store in the temporaryToDos the items that meet the text filter
        if (searchString != "") {
            for (todo toDoElem : toDoList) {
                if (toDoElem.getText().contains(searchString)) {
                    temporaryToDos.add(toDoElem);
                } 
            }
        } else {
            temporaryToDos = toDoList; 
        }

        // No prorities and state filters were applied
        if(showAllPriorities && showAllStates) {
            return temporaryToDos;
        }

        for (todo toDoElem : temporaryToDos) {
            // Obatain priority
            SearchPriority todoPriority = SearchPriority.valueOf(toDoElem.getPriority().name());

    
            // From the to-do doneFlag assign an enum SearchState 
            SearchState toDoState = SearchState.All;
            if (toDoElem.getDoneFlag()) {
                toDoState = SearchState.Done;
            } else {
                toDoState = SearchState.Undone;
            }

            // Priority and state filter
            if ((!showAllPriorities && todoPriority == searchPriority) && (!showAllStates && toDoState == searchState)) {
                filteredToDos.add(toDoElem);
            } else if (showAllPriorities && toDoState == searchState) { // Only state filter
                filteredToDos.add(toDoElem);
            } else if (showAllStates && todoPriority == searchPriority) { // Only priority filter
                filteredToDos.add(toDoElem);
            }
        }
        
        return filteredToDos;
    }

    @Override
    public todo update(String id, todo updatedToDo) {
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                
                // Validations
                validateText(updatedToDo.getText());
     
                // Update values
                toDoElem.setText(updatedToDo.getText());
                toDoElem.setPriority(updatedToDo.getPriority());
                toDoElem.setDueDate(updatedToDo.getDueDate());
                return toDoElem;
            } 
            
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }
    @Override
    public String deleteToDo(String id) {
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                toDoList.remove(toDoElem);

                return "To do item deleted successfully";
            }
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }

    public todo setDoneToDo(String id) {
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                // Mark to do as done
                toDoElem.setDoneFlag(true);
                // Set done date
                LocalDate newDoneDate = LocalDate.now();
                Optional<LocalDate> optionalDate = Optional.ofNullable(newDoneDate);
                toDoElem.setDoneDate(optionalDate);

                return toDoElem;
            }
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }

    public todo setUndoneToDo(String id) {
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                // Mark to do as undone
                toDoElem.setDoneFlag(false);
                // Clean done date
                Optional<LocalDate> optionalDate = Optional.empty();
                toDoElem.setDoneDate(optionalDate);

                return toDoElem;
            }
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }

    public void validateText(String text) {
        int textLength = text.length();
        if (textLength == 0 ) {
            throw new IllegalArgumentException("Please add a to do description");
        } else if (textLength > 120) {
            throw new IllegalArgumentException("The to do description should be at most 120 characters");
        }
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
