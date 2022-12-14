package com.example.kdemo.todo

import io.restassured.module.mockmvc.kotlin.extensions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(SpringExtension::class)
class TodoControllerTests {
    private val mockMvc = MockMvcBuilders.standaloneSetup(TodosController()).build()

    @Test
    fun `get todos returns status code 200`() {
        Given {
            mockMvc(mockMvc)
        } When {
            get("/todos")
        } Then {
            statusCode(200)
        }

    }
}

