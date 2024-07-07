package com.todo.todosystem.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.todosystem.exception.ResourceNotFoundException;
import com.todo.todosystem.model.Metrics;
import com.todo.todosystem.model.Priority;
import com.todo.todosystem.model.SearchPriority;
import com.todo.todosystem.model.SearchState;
import com.todo.todosystem.model.SearchTodo;
import com.todo.todosystem.model.todo;
import com.todo.todosystem.repository.todoRepository;





@Service
public class todoServiceImpl {

    private List<todo> toDoList = new ArrayList<todo>();
    private List<todo> filteredToDoList= new ArrayList<todo>();
    
    private long sumSecondsTotal = 0L;
    private long sumTimeLowTasks = 0L;
    private long sumTimeMediumTasks = 0L;
    private long sumTimeHighTasks = 0L;

    private int lowCounter = 0;
    private int mediumCounter = 0;
    private int highCounter = 0;

    private long averageMinutesLow = 0L;
    private long averageMinutesMedium = 0L;
    private long averageMinutesHigh = 0L;
        
    private long averageSecondsTotal = 0L;
    private long averageSecondsLow = 0L;
    private long averageSecondsMedium = 0L;
    private long averageSecondsHigh = 0L;

 
    public todo getToDoById(String id) {
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                return toDoElem;
            } 
            
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }

    public List<todo> getPaginatedToDo(int page, int size) {
        int indx = (page - 1) * size;
        int startIndex = indx > 0 ? indx : 0;
        int endIndex = Math.min(startIndex + size, filteredToDoList.size());
        System.out.println("startIndex" + startIndex);
        System.out.println("endIndex" + endIndex);

        if (startIndex >= filteredToDoList.size()) {
            return new ArrayList<>();
        }
       
        System.out.println(filteredToDoList.subList(startIndex, endIndex));
        return filteredToDoList.subList(startIndex, endIndex);
    }


    public todo saveTodo(todo newToDo) {
        // Validations
        validateText(newToDo.getText());
        // Add Id
        newToDo.setId(UUID.randomUUID().toString()); 
        toDoList.add(newToDo);
        return newToDo;
    }

  
    public List<todo> getTodos() {
        return toDoList;
    }


    public List<todo> getFilteredToDos(String text, SearchPriority priority, SearchState state) {
        // Empty filtered to dos from previous searches
        filteredToDoList.clear();

        List<todo> temporaryToDos = new ArrayList<todo>();

        SearchTodo searchTodoItem = new SearchTodo(text, priority, state);

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
            filteredToDoList = toDoList;
        }

        // No prorities and state filters were applied
        if(showAllPriorities && showAllStates) {
            filteredToDoList = temporaryToDos;
            return filteredToDoList;
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
                filteredToDoList.add(toDoElem);
            } else if (showAllPriorities && toDoState == searchState) { // Only state filter
                filteredToDoList.add(toDoElem);
            } else if (showAllStates && todoPriority == searchPriority) { // Only priority filter
                filteredToDoList.add(toDoElem);
            }
        }
        
        return filteredToDoList;
    }

   
    public List<todo> updateToDo(String id, todo updatedToDo) {
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                
                // Validations
                validateText(updatedToDo.getText());
     
                // Update values
                toDoElem.setText(updatedToDo.getText());
                toDoElem.setPriority(updatedToDo.getPriority());
                toDoElem.setDueDate(updatedToDo.getDueDate());
                return toDoList;
            } 
            
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }

  
    public List<todo> deleteToDo(String id) {
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                toDoList.remove(toDoElem);

                return toDoList;
            }
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }

   
    public Metrics setDoneToDo(String id) {
        
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                // Mark to do as done
                toDoElem.setDoneFlag(true);
                // Set done date
                LocalDateTime newDoneDate = LocalDateTime.now();
                Optional<LocalDateTime> optionalDate = Optional.ofNullable(newDoneDate);
                toDoElem.setDoneDate(optionalDate);

                // Include this to do in the metrics
                LocalDateTime notOptionalDoneDate = toDoElem.getDoneDate().orElseThrow();
                Metrics metrics = calculateAverageTime(toDoElem.getCreationDate(), notOptionalDoneDate, toDoElem.getPriority());
                
                return metrics;
            }
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }

    
    public Metrics setUndoneToDo(String id) {
        for (todo toDoElem : toDoList) {
            if (toDoElem.getId().equals(id)) {
                System.out.println(toDoElem.getText());
                // Mark to do as undone
                toDoElem.setDoneFlag(false);

                // Update the metrics by removing the undone to do
                LocalDateTime notOptionalDoneDate = toDoElem.getDoneDate().orElseThrow();
                Metrics metrics = removeFromAverageTime(toDoElem.getCreationDate(), notOptionalDoneDate, toDoElem.getPriority());

                // Clean done date
                Optional<LocalDateTime> optionalDate = Optional.empty();
                toDoElem.setDoneDate(optionalDate);

                return metrics;
            }
        }
        throw new ResourceNotFoundException("The to do item was not found");
    }
    // @Override
    // public Page<todo> getPaginatedToDos(int pageNo, int pageSize) {
    //     PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
    //     return this.findAll(pageRequest);
    // }

   
    public void validateText(String text) {
        int textLength = text.length();
        if (textLength == 0 ) {
            throw new IllegalArgumentException("Please add a to do description");
        } else if (textLength > 120) {
            throw new IllegalArgumentException("The to do description should be at most 120 characters");
        }
    }


    public Metrics calculateAverageTime(LocalDateTime creationDate, LocalDateTime doneDate, Priority priority) {
        Duration duration  = Duration.between(creationDate, doneDate);
        long seconds = duration.getSeconds();
        sumSecondsTotal += seconds;

        switch(priority) {
            case Low:
                sumTimeLowTasks += seconds;
                lowCounter += 1;
                averageSecondsLow = sumTimeLowTasks / lowCounter;
                averageMinutesLow = averageSecondsLow / 60;
                averageSecondsLow %= 60;
                break;
            case Medium:
                sumTimeMediumTasks += seconds;
                mediumCounter += 1;
                averageSecondsMedium = sumTimeMediumTasks / mediumCounter;
                averageMinutesMedium = averageSecondsMedium / 60;
                averageSecondsMedium %= 60;
                break;
            default:
                sumTimeHighTasks += seconds;
                highCounter += 1;
                averageSecondsHigh = sumTimeHighTasks / highCounter;
                averageMinutesHigh = averageSecondsHigh / 60;
                averageSecondsHigh %= 60;
        }

     
        averageSecondsTotal = sumSecondsTotal / (lowCounter + mediumCounter + highCounter);
        long averageMinutesTotal = averageSecondsTotal / 60;
        averageSecondsTotal %= 60;
         

        String strAverageSecondsTotal = Long.toString(averageSecondsTotal);
        String strAverageSecondsLow = Long.toString(averageSecondsLow);
        String strAverageSecondsMedium = Long.toString(averageSecondsMedium);
        String strAverageSecondsHigh = Long.toString(averageSecondsHigh);


        String aveTimeTotal = averageMinutesTotal + ":" + ((strAverageSecondsTotal.length() == 1) ? "0" + strAverageSecondsTotal : strAverageSecondsTotal);
        String aveTimeLow = averageMinutesLow + ":" + ((strAverageSecondsLow.length() == 1) ? "0" + strAverageSecondsLow : strAverageSecondsLow);
        String aveTimeMedium = averageMinutesMedium + ":" + ((strAverageSecondsMedium.length() == 1) ? "0" + strAverageSecondsMedium : strAverageSecondsMedium);
        String aveTimeHigh = averageMinutesHigh + ":" + ((strAverageSecondsHigh.length() == 1) ? "0" + strAverageSecondsHigh : strAverageSecondsHigh);
    
        Metrics metrics = new Metrics(aveTimeTotal, aveTimeLow, aveTimeMedium, aveTimeHigh);
        return metrics;
    }

  
    public Metrics removeFromAverageTime(LocalDateTime creationDate, LocalDateTime doneDate, Priority priority) {
        Duration duration  = Duration.between(creationDate, doneDate);
        long seconds = duration.getSeconds();
        sumSecondsTotal -= seconds;

    
        switch(priority) {
            case Low:
                sumTimeLowTasks -= seconds;
                lowCounter -= 1;
                averageSecondsLow = (lowCounter == 0) ? 0 : sumTimeLowTasks / lowCounter;
                averageMinutesLow = averageSecondsLow / 60;
                averageSecondsLow %= 60;
                break;
            case Medium:
                sumTimeMediumTasks -= seconds;
                mediumCounter -= 1;
                averageSecondsMedium = (mediumCounter == 0) ? 0 : sumTimeMediumTasks / mediumCounter;
                averageMinutesMedium = averageSecondsMedium / 60;
                averageSecondsMedium %= 60;
                break;
            default:
                sumTimeHighTasks -= seconds;
                highCounter -= 1;
                averageSecondsHigh = (highCounter == 0) ? 0 : sumTimeHighTasks / highCounter;
                averageMinutesHigh = averageSecondsHigh / 60;
                averageSecondsHigh %= 60;
        }

        int counterTotal = lowCounter + mediumCounter + highCounter;
        averageSecondsTotal = sumSecondsTotal / ((counterTotal == 0) ? 1 : counterTotal);
        long averageMinutesTotal = averageSecondsTotal / 60;
        averageSecondsTotal %= 60;
         

        String strAverageSecondsTotal = Long.toString(averageSecondsTotal);
        String strAverageSecondsLow = Long.toString(averageSecondsLow);
        String strAverageSecondsMedium = Long.toString(averageSecondsMedium);
        String strAverageSecondsHigh = Long.toString(averageSecondsHigh);

        String aveTimeTotal = averageMinutesTotal + ":" + ((strAverageSecondsTotal.length() == 1) ? "0" + strAverageSecondsTotal : strAverageSecondsTotal);
        String aveTimeLow = averageMinutesLow + ":" + ((strAverageSecondsLow.length() == 1) ? "0" + strAverageSecondsLow : strAverageSecondsLow);
        String aveTimeMedium = averageMinutesMedium + ":" + ((strAverageSecondsMedium.length() == 1) ? "0" + strAverageSecondsMedium : strAverageSecondsMedium);
        String aveTimeHigh = averageMinutesHigh + ":" + ((strAverageSecondsHigh.length() == 1) ? "0" + strAverageSecondsHigh : strAverageSecondsHigh);
    
        Metrics metrics = new Metrics(aveTimeTotal, aveTimeLow, aveTimeMedium, aveTimeHigh);
        return metrics;
    }

  

    
    

   
    // public Page<todo> getAllToDos(int pageNo, int pageSize) { 
    //     PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
    //     return repository.findAll(pageRequest);
      
    // }

    // public todo getToDoById(String id) {
    //     return repository.getTodo(id);
    // }

    // public List<todo> getTodos() {
    //     return repository.getTodos();
    // }

    // public List<todo> searchToDos(String text, SearchPriority priority, SearchState state) {
    //     return repository.getFilteredToDos(text, priority, state);
    // }

    // public Page<todo> findPaginated(int page, int size) {
      
    //     Pageable pageable = PageRequest.of(page, size);
    //     return repository.findAll(pageable);
    // }

    // public todo save(todo todoItem) {
    //     return repository.saveTodo(todoItem);
    // }

    // public List<todo> update(String id, todo todoItem) {
    //     return repository.update(id, todoItem);
    // }
    // public List<todo> delete(String id) {
    //     return repository.deleteToDo(id);
    // }

    // public Metrics setDone(String id) {
    //     return repository.setDoneToDo(id);
    // }
    // public Metrics setUndone(String id) {
    //     return repository.setUndoneToDo(id);
    // }
}
