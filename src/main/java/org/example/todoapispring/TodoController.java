package org.example.todoapispring;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TodoController {
    private static List<Todo> todos = new ArrayList<>();

    public TodoController() {
        todos = new ArrayList<>();
        todos.add(new Todo(1,false,"Todo 1",1));
        todos.add(new Todo(2,false,"Todo 2",2));
    }
}
