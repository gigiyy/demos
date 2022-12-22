package com.example.fcb.play;

import java.util.function.Consumer;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StringProcessor {

    private static final Logger logger = LoggerFactory.getLogger(StringProcessor.class);

    @Bean
    public Function<String, String> uppercase() {
        return input -> {
            logger.info("Got {}", input);
            return input.toUpperCase();
        };
    }

    @Bean
    public Consumer<String> display() {
        return input -> logger.info("Received {}", input);
    }
}
