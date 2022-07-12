package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class ClaimsEndpointTests {

    @Autowired
    private ClaimController claimController;

    @Autowired
    private ClaimRepository claimRepository;

    @Test
    public void submitClaimShouldSaveToDb() throws JsonProcessingException {
        ClaimData data = new ClaimData("XXXXXX", "YYYYYY", "the claim amount is 2000USD!");

        WebTestClient client = MockMvcWebTestClient.bindToController(claimController).build();

        client.post().uri("/claims").body(Mono.just(data), ClaimData.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        List<Claim> found = claimRepository.findBySender("XXXXXX");
        assertTrue(found.size() > 0);
        assertEquals(found.get(0).getReceiver(), "YYYYYY");
    }
}
