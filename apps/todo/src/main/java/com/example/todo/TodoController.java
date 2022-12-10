package com.example.todo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
