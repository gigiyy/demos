package com.example.fcb.swallow;

import com.example.fcb.claim.service.Claim;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwiftSender {

    private static final Logger logger = LoggerFactory.getLogger(SwiftSender.class);

    @Bean
    public Consumer<Claim> sendClaim() {
        return claim -> {
            logger.info("Will send claim message of {}", claim);
        };
    }
}
