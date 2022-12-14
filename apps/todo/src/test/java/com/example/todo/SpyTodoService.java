package com.example.todo;

import java.util.List;

public class SpyTodoService implements TodoService {

    private boolean allTodosIscalled;
    private List<Todo> todos;
    private Todo updateTodo_paramValue;
    private Long findTodo_paramValue;
    private Todo findTodo_returnTodo;
    private boolean throwNotFoundException;

    public boolean getAllTodosIscalled() {
        return allTodosIscalled;
    }

    @Override
    public List<Todo> getAllTodos() {
        allTodosIscalled = true;
        return todos;
    }

    @Override
    public void updateTodo(Todo todo) {
        updateTodo_paramValue = todo;
    }

    @Override
    public Todo findTodo(Long id) {
        if (throwNotFoundException) {
            throw new TodoNotFoundException("not found");
        }
        findTodo_paramValue = id;
        return findTodo_returnTodo;
    }


    public void setGetAllTodos_returnAllTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public Todo getUpdateTodo_paramValue() {
        return updateTodo_paramValue;
    }

    public Long getFindTodo_paramValue() {
        return findTodo_paramValue;
    }

    public void setFindTodo_returnTodo(Todo findTodo_returnTodo) {
        this.findTodo_returnTodo = findTodo_returnTodo;
    }

    public void setFindTodo_throwNotFoundException() {
        throwNotFoundException = true;
    }
}
