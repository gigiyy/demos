package com.example.fcb.play;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UrlController {

    @GetMapping("/get-slash/")
    @ResponseStatus(HttpStatus.OK)
    String getWithSlash() {
        return "OK";
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    String getWithoutSlash() {
        return "OK";
    }

    @PostMapping("/post-slash/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    String postWithSlash() {
        return "OK";
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.ACCEPTED)
    String postWithoutSlash() {
        return "OK";
    }


}
