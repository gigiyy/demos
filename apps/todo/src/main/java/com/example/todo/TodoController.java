package com.example.todo;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable Long id) {
        return todoService.findTodo(id);
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public void postTodo(@RequestParam String title) {
        todoService.updateTodo(new Todo(null, title));
    }

    @PutMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTodo(@PathVariable Long id, @RequestParam String title) {
        Todo current = todoService.findTodo(id);
        current.title = title;
        todoService.updateTodo(current);
    }
}

@ControllerAdvice
class TodoControllerAdvice {

    @ResponseBody
    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String todoNotFoundHandler(TodoNotFoundException e) {
        return e.getMessage();
    }
}
