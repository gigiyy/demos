package com.example.demo.state;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {

    private StateService stateService;

    public StateController(StateService stateService) {

        this.stateService = stateService;
    }
    @PostMapping("/states")
    public String saveSate(@RequestBody StateRequest request) {
        stateService.saveSate(request.toCommand());
        return "OK";
    }
}
