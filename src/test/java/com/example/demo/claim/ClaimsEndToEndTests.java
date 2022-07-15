package com.example.demo.claim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClaimsEndToEndTests {
    @LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;
    WebTestClient client;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:" + port;
        client = WebTestClient.bindToServer().baseUrl(url).build();
    }

    @Test
    public void submitClaimsShouldSaveData() {
        ClaimData data = new ClaimData("XXXXXX", "YYYYYY", "the claim amount is 2000USD!");

        client.post().uri("/claims").body(Mono.just(data), ClaimData.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().jsonPath("$.id").exists();
    }

    @Test
    public void submitClaimsWithRestTemplateShouldSaveData() {
        ClaimData data = new ClaimData("XXXXXX", "YYYYYY", "the claim amount is 2000USD!");

        ResponseEntity<Claim> result = restTemplate.postForEntity(url + "/claims", data, Claim.class);

        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        assertTrue(result.getBody().getId() > 0);
    }
}
