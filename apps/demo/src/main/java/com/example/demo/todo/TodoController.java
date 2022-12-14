package com.example.demo.todo;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {

        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<Todo> todoList() {
        return todoService.getAllTodos();
    }

}
