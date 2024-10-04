package org.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList = new ArrayList<>();
    private static final String TODO_NOT_FOUND = "Todo not found";

    public TodoController() {
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, false, "Todo 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean completed) {
        if (completed == null) {
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } else {
            List<Todo> filteredTodos = new ArrayList<>();
            for (Todo todo : todoList) {
                if (todo.isCompleted() == completed) {
                    filteredTodos.add(todo);
                }
            }
            return new ResponseEntity<>(filteredTodos, HttpStatus.OK);
        }
    }

    @GetMapping("/minId/{id}")
    public ResponseEntity<List<Todo>> getTodoById(@PathVariable Integer id) {
        todoList.sort(Comparator.comparing(Todo::getId));
        List<Todo> filteredTodos = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.getId() >= id) {
                filteredTodos.add(todo);
            }
        }
        return new ResponseEntity<>(filteredTodos, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
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

    @PatchMapping("/{id}")
    ResponseEntity<?> updateTodoById(@PathVariable Long id,
                                     @RequestParam(required = false) String title,
                                     @RequestParam(required = false) Boolean completed
                                     ) {
        for(Todo todo : todoList) {
            if (todo.getId() == id) {
                if(title!=null){
                    todo.setTitle(title);
                }
                if(completed!=null){
                    todo.setCompleted(completed);
                }
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);


    }

/**
 * API to delete a Todo
 */
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTodoById(@PathVariable int id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                todoList.remove(todo);
                String Sucess = "Todo successfully deleted";
                return ResponseEntity.ok(Sucess);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }


    /*@PutMapping("/{id}")
//    put changes the full object
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo updatedTodo) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                todo.setCompleted(updatedTodo.isCompleted());
                todo.setTitle(updatedTodo.getTitle());
                todo.setUserId(updatedTodo.getUserId());
                return new ResponseEntity<>(todo, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    */



}
