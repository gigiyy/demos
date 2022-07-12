package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClaimsEndToEndTests {
    @LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:" + port;
    }

    @Test
    public void submitClaimsShouldSaveData() {
        ClaimData data = new ClaimData("XXXXXX", "YYYYYY", "the claim amount is 2000USD!");
        WebTestClient client = WebTestClient.bindToServer().baseUrl(url).build();

        client.post().uri("/claims").body(Mono.just(data), ClaimData.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    public void submitClaimsWithRestTemplateShouldSaveData() {
        ClaimData data = new ClaimData("XXXXXX", "YYYYYY", "the claim amount is 2000USD!");
        HttpEntity<ClaimData> request = new HttpEntity<>(data);
        ResponseEntity<Void> result = restTemplate.postForEntity(url + "/claims", data, Void.class);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}
