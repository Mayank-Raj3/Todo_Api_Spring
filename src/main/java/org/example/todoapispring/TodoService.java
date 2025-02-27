package org.example.todoapispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

//    private static final String TODO_NOT_FOUND = "Todo not found";

    public List<Todo> getToDoLists(){
        return (List<Todo>) todoRepository.findAll();
    }


    public boolean createNewTodo(Todo newTodo){
        todoRepository.save(newTodo);
        return true;
    }

}
