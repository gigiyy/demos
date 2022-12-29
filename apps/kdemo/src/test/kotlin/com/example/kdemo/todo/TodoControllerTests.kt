package com.example.kdemo.todo

import io.restassured.module.mockmvc.kotlin.extensions.Given
import io.restassured.module.mockmvc.kotlin.extensions.Then
import io.restassured.module.mockmvc.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class TodoControllerTests {
    @Test
    fun `get todos returns status code 200`() {
        Given {
            standaloneSetup(TodosController())
        } When {
            get("/todos")
        } Then {
            statusCode(200)
            body("id", equalTo("id"))
            body("name", equalTo("todo"))
        }

    }
}

