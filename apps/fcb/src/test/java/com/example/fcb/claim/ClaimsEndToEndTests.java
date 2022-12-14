package com.example.fcb.claim;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.fcb.claim.service.Claim;
import com.example.fcb.claim.service.ClaimData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8082)
public class ClaimsEndToEndTests {

    WebTestClient client;
    @LocalServerPort
    private int port;
    private String url;
    @Autowired
    private TestRestTemplate restTemplate;

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

        ResponseEntity<Claim> result = restTemplate.postForEntity(url + "/claims", data,
            Claim.class);

        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        assertTrue(result.getBody().getId() > 0);
    }


    @Test
    public void getClaimShouldReturnClaimData() throws Exception {
        stubFor(get("/forex/eur").willReturn(aResponse()
            .withHeader("Content-type", "application/json")
            .withBody(
                // language=json
                """
                    {
                        "currency": "eur",
                        "toTwd":45.3,
                        "toUsd":1.2
                    }"""
            )));
        client.get().uri(uriBuilder -> uriBuilder.path("claims").queryParam("seqNo", "abc").build())
            .exchange()
            .expectStatus().isOk()
            .expectBody().jsonPath("$.message").value(containsString("Currency: EUR, Rate: 45.3"));
    }
}
