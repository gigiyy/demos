package com.example.demo.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMember() {

    }
}
