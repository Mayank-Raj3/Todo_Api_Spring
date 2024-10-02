package org.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList = new ArrayList<>();
    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1",1));
        todoList.add(new Todo(2,false,"Todo 2",2));
    }
    @GetMapping()
    public ResponseEntity<List<Todo>> getTodos() {
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> createTodos(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
   public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                return new ResponseEntity<>(todo, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
