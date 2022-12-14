package com.example.todo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoServiceTest {

    TodoService todoService;
    SpyTodoRepository spyTodoRepository;

    @BeforeEach
    void setUp() {

        spyTodoRepository = new SpyTodoRepository();
        todoService = new TodoServiceImpl(spyTodoRepository);
    }

    @Test
    public void getAllTodos_then_callsFindAll() {
        spyTodoRepository.setFindAll_returnValue(List.of(new Todo(1L, "dummy title")));
        List<Todo> returned = todoService.getAllTodos();
        assertThat(returned, equalTo(List.of(new Todo(1L, "dummy title"))));

        assertThat(spyTodoRepository.getFindAllIsCalled(), is(true));
    }

    @Test
    public void addTodo_then_save() {
        todoService.updateTodo(new Todo(null, "good title"));
        Todo saved = spyTodoRepository.getSave_paramValue();
        assertThat(saved.getTitle(), is("good title"));
    }

    @Test
    public void findTodo_returnValue() {
        spyTodoRepository.setFindById_returnValue(new Todo(1L, "dummy"));
        Todo todo = todoService.findTodo(1L);
        assertThat(todo, equalTo(new Todo(1L, "dummy")));

    }
}