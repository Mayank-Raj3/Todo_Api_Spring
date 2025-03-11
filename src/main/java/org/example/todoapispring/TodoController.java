package org.example.todoapispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    @Autowired
    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> fetchedList = todoService.getToDoLists();
        return new ResponseEntity<>(fetchedList, HttpStatus.OK);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {

        boolean success = todoService.createNewTodo(newTodo);
        if(success){
            return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        }

}

//    @GetMapping("/minId/{id}")
//    public ResponseEntity<List<Todo>> getTodoById(@PathVariable Integer id) {
//        todoList.sort(Comparator.comparing(Todo::getId));
//        List<Todo> filteredTodos = new ArrayList<>();
//        for (Todo todo : todoList) {
//            if (todo.getId() >= id) {
//                filteredTodos.add(todo);
//            }
//        }
//        return new ResponseEntity<>(filteredTodos, HttpStatus.OK);
//    }
//

//
//    @GetMapping("/{id}")
//    public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
//        for (Todo todo : todoList) {
//            if (todo.getId() == id) {
//                return new ResponseEntity<>(todo, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
//
//    @PatchMapping("/{id}")
//    ResponseEntity<?> updateTodoById(@PathVariable Long id,
//                                     @RequestParam(required = false) String title,
//                                     @RequestParam(required = false) Boolean completed
//                                     ) {
//        for(Todo todo : todoList) {
//            if (todo.getId() == id) {
//                if(title!=null){
//                    todo.setTitle(title);
//                }
//                if(completed!=null){
//                    todo.setCompleted(completed);
//                }
//                return ResponseEntity.ok(todo);
//            }
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
//
//
//    }
//
///**
// * API to delete a Todo
// */
//    @DeleteMapping("/{id}")
//    ResponseEntity<?> deleteTodoById(@PathVariable int id) {
//        for (Todo todo : todoList) {
//            if (todo.getId() == id) {
//                todoList.remove(todo);
//                String Sucess = "Todo successfully deleted";
//                return ResponseEntity.ok(Sucess);
//            }
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
//    }

