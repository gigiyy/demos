package com.example.fcb.claim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.fcb.claim.error.ClaimExceptionHandler;
import com.example.fcb.claim.service.Claim;
import com.example.fcb.claim.service.ClaimData;
import com.example.fcb.claim.service.ClaimRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
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
        client = MockMvcWebTestClient.bindToController(claimController, claimExceptionHandler)
            .build();
    }

    @Test
    public void submitClaimShouldSaveToDb() {
        ClaimData data = new ClaimData("XXXXXX", "YYYYYY", "the claim amount is 2000USD!");

        client.post().uri("/claims").body(Mono.just(data), ClaimData.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().jsonPath("$.id").isNumber();

        List<Claim> found = claimRepository.findBySender("XXXXXX");
        assertTrue(found.size() > 0);
        assertEquals(found.get(0).getReceiver(), "YYYYYY");
    }

    @Test
    public void sendClaimMessageNotExistsShouldReturnNotFound() {
        client.put().uri("/claims/404/send").exchange().expectStatus().isNotFound();
    }
}
