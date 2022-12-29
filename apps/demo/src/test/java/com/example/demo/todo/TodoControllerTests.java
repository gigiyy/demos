package com.example.demo.todo;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TodoControllerTests {

    SpyTodoService spyTodoService;

    @BeforeEach
    public void setup() {
        spyTodoService = new SpyTodoService();
        RestAssuredMockMvc.standaloneSetup(new TodoController(spyTodoService));
    }

    @Test
    public void get_todos_returns_status_OK() {
        spyTodoService.setGetAllTodos_returnAllTodos(Collections.emptyList());
        given()
            .when()
            .get("/todos")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

    @Test
    public void get_todos_then_call_todoService_getAllTodos() {
        spyTodoService.setGetAllTodos_returnAllTodos(List.of(new Todo(1L, "dummy title")));
        given()
            .when()
            .get("/todos")
            .then()
            .body("[0].title", equalTo("dummy title"));

        assertThat(spyTodoService.getAllTodosIscalled(), is(true));

    }
}
