package com.example.fcb.swallow;

import com.example.fcb.claim.service.Claim;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SwiftSender {

    @Bean
    public Consumer<Claim> sendClaim() {
        return claim -> {
            log.info("Will send claim message of {}", claim);
        };
    }
}
