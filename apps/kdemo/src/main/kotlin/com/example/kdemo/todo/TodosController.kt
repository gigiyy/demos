package com.example.kdemo.todo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodosController {

    @GetMapping("/todos")
    fun todos(): Todo {
        return Todo("id", "todo")
    }
}

data class Todo(val id: String, val name: String)