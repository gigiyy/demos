package com.example.todo;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodos();

    void updateTodo(Todo todo);

    Todo findTodo(Long id);
}
