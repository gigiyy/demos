package com.example.todo;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpStatus.*;

public class TodoControllerTests {
    SpyTodoService spyTodoService;

    @BeforeEach
    public void setup() {
        spyTodoService = new SpyTodoService();
        RestAssuredMockMvc.standaloneSetup(new TodoController(spyTodoService), new TodoControllerAdvice());
    }

    @Test
    public void getTodos_returns_status_OK() {
        spyTodoService.setGetAllTodos_returnAllTodos(Lists.emptyList());
        given()
                .when()
                .get("/todos")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);
    }

    @Test
    public void geTodos_call_todoService_getAllTodos() {
        spyTodoService.setGetAllTodos_returnAllTodos(List.of(new Todo(1L, "dummy title")));
        given()
                .when()
                .get("/todos")
                .then()
                .body("[0].title", equalTo("dummy title"));

        assertThat(spyTodoService.getAllTodosIscalled(), is(true));

    }

    @Test
    public void getTodo_call_todoService_findTodo() {
        spyTodoService.setFindTodo_returnTodo(new Todo(1L, "dummy title"));
        given()
                .when()
                .get("/todos/1")
                .then()
                .statusCode(OK.value())
                .body("id", equalTo(1))
                .body("title", equalTo("dummy title"));
        assertThat(spyTodoService.getFindTodo_paramValue(), equalTo(1L));
    }

    @Test
    public void getTodo_return_notFound_for_notExistingItem() {
        spyTodoService.setFindTodo_throwNotFoundException();
        given()
                .when()
                .get("/todos/1")
                .then()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    public void postTodos_returns_status_created() {
        given()
                .param("title", "good title")
                .when()
                .post("/todos")
                .then()
                .statusCode(CREATED.value());

        Todo param = spyTodoService.getUpdateTodo_paramValue();
        assertThat(param.getTitle(), is("good title"));
    }

    @Test
    public void putTodos_returns_status_noContent() {
        spyTodoService.setFindTodo_returnTodo(new Todo(1L, "old Title"));
        given()
                .param("title", "good title")
                .put("/todos/1")
                .then()
                .statusCode(NO_CONTENT.value());

        Todo param = spyTodoService.getUpdateTodo_paramValue();
        assertThat(param, equalTo(new Todo(1L, "good title")));
    }

    @Test
    public void putTodo_returns_notFound_for_notExistingItem() {
        spyTodoService.setFindTodo_throwNotFoundException();
        given()
                .param("title", "not there")
                .put("/todos/1")
                .then()
                .statusCode(NOT_FOUND.value());
    }
}
