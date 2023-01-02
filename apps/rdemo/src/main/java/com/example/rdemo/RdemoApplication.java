package com.example.rdemo;

import java.util.Random;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class RdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RdemoApplication.class, args);
    }

    String[] senders = {"aaa", "bbb", "ccc"};
    String[] receivers = {"xxx", "yyy", "zzz"};
    Random random = new Random();

    @Bean
    public CommandLineRunner loadData(ClaimRepository repository) {
        return args ->
            IntStream.rangeClosed(1, 10)
                .forEach(i ->
                    repository.save(generateClaim())
                        .subscribe(claim -> log.info("generated {}", claim))
                );
    }

    private Claim generateClaim() {
        String sender = senders[random.nextInt(senders.length)];
        String receiver = receivers[random.nextInt(receivers.length)];
        String message = "please send me " + random.nextInt(1000) + " dollars!";
        return Claim.builder()
            .sender(sender)
            .receiver(receiver)
            .message(message)
            .build();
    }
}
