package com.example.kdemo.todo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodosController {

    @GetMapping("/todos")
    fun todos(): String {
        return ""
    }
}