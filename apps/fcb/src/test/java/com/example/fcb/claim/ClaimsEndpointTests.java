package com.example.fcb.claim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.fcb.claim.error.ClaimExceptionHandler;
import com.example.fcb.claim.service.ClaimEntity;
import com.example.fcb.claim.service.ClaimData;
import com.example.fcb.claim.service.ClaimRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest
public class ClaimsEndpointTests {

    @Autowired
    ClaimExceptionHandler claimExceptionHandler;
    WebTestClient client;
    @Autowired
    private ClaimController claimController;
    @Autowired
    private ClaimRepository claimRepository;

    @BeforeEach
    public void before() {
        ClaimExceptionHandler claimExceptionHandler = new ClaimExceptionHandler();
        client = WebTestClient.bindToController(claimController, claimExceptionHandler)
            .build();
    }

    @Test
    public void submitClaimShouldSaveToDb() {
        ClaimData data = new ClaimData("123456", "XXXXXX", "YYYYYY", "the claim amount is 2000USD!");

        client.post().uri("/claims").body(Mono.just(data), ClaimData.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().jsonPath("$.id").isNumber();

        List<ClaimEntity> found = claimRepository.findBySender("XXXXXX");
        assertTrue(found.size() > 0);
        assertEquals(found.get(0).getReceiver(), "YYYYYY");
    }

    @Test
    public void sendClaimMessageNotExistsShouldReturnNotFound() {
        client.put().uri("/claims/404/send").exchange().expectStatus().isNotFound();
    }
}
