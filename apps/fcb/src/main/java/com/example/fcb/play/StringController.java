package com.example.fcb.play;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@Slf4j
public class StringController {

    private StreamBridge streamBridge;

    public StringController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @GetMapping
    public String getMessage() {
        return "sample";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestBody String content) {
        log.info("sending {}", content);
        streamBridge.send("send-out-0", content);
    }
}
