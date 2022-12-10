package com.example.fcb.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StateControllerTests {

    private Logger logger = LoggerFactory.getLogger("TestClass");

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;


    WebTestClient client;

    String baseUrl;

    @BeforeEach
    public void initialize() {
        baseUrl = "http://localhost:" + port;
        client = WebTestClient.bindToServer().baseUrl(baseUrl).build();
    }


    @Test
    public void sendPostRequest() {

        StateRequest request = new StateRequest("uuid1", "name", "uuid1");

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/states", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void sendParallelRequests() {
        List<StateRequest> requests = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> UUID.randomUUID().toString())
                .parallel()
                .map(uuid -> new StateRequest(uuid, "abc", uuid))
                .peek(request -> {
                    logger.info("sending request with {}", request.getUuid());
                    client.post().uri("/states").body(Mono.just(request), StateRequest.class)
                            .exchange().expectStatus().isOk().expectBody(String.class);
                }).toList();

        requests.forEach(request -> logger.info(request.toString()));
    }
}