package com.example.rdemo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClaimControllerTests {

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private ClaimController claimController;
    private WebTestClient client;


    @BeforeEach
    public void setup() {
        client = WebTestClient.bindToController(claimController).build();
    }

    @Test
    public void submitClaimShouldReturnStatusCreated() throws Exception {
        Claim data = new Claim(null, "XXXX", "YYYY", "message");
        Claim result = new Claim(100L, "XXXX", "YYYY", "message");
        when(claimRepository.save(any())).thenReturn(Mono.just(result));

        client.post().uri("/claims").bodyValue(data)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().jsonPath("$.id").isNumber();
    }

    @Test
    public void submitClaimWithInvalidDataShouldReturnStatusBadRequest() {
        Claim data = new Claim(null, "XX", "YYY", "message");
        client.post().uri("/claims").bodyValue(data)
                .exchange()
                .expectStatus().isBadRequest();
    }
}

