package com.example.fcb.play;

import java.util.function.Consumer;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class StringProcessor {

    @Bean
    public Function<String, String> uppercase() {
        return input -> {
            log.info("Got {}", input);
            return input.toUpperCase();
        };
    }

    @Bean
    public Consumer<String> display() {
        return input -> log.info("Received {}", input);
    }
}
