package com.example.demo.todo;

import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

public class SpyTodoService implements TodoService {

    private boolean allTodosIscalled;
    private List<Todo> todos;

    public boolean getAllTodosIscalled() {
        return allTodosIscalled;
    }

    @Override
    public List<Todo> getAllTodos() {
        allTodosIscalled = true;
        return todos;
    }

    public void setGetAllTodos_returnAllTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
