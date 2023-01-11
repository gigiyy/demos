package com.example.fcb.play;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UrlControllerTest {

    @LocalServerPort
    int port;

    WebTestClient client;

    String url;

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + port;
        client = WebTestClient.bindToServer().baseUrl(url).build();
    }

    @Test
    void testGetWithSlash_sendingNoSlash() {
        client.get().uri("/test/get-slash").exchange().expectStatus().isNotFound();
    }

    @Test
    void testGetWithSlash_sendingSlash() {
        client.get().uri("/test/get-slash/").exchange().expectStatus().isOk();
    }

    @Test
    void testGetWithoutSlash_sendingNoSlash() {
        client.get().uri("/test/get").exchange().expectStatus().isOk();
    }

    @Test
    void testGetWithoutSlash_sendingSlash() {
        client.get().uri("/test/get/").exchange().expectStatus().isOk();
    }

    @Test
    void testPostWithSlash_sendingNoSlash() {
        client.post().uri("/test/post-slash").exchange().expectStatus().isNotFound();
    }

    @Test
    void testPostWithSlash_sendingSlash() {
        client.post().uri("/test/post-slash/").exchange().expectStatus().isAccepted();
    }

    @Test
    void testPostWithoutSlash_sendingNoSlash() {
        client.post().uri("/test/post").exchange().expectStatus().isAccepted();
    }

    @Test
    void testPostWithoutSlash_sendingSlash() {
        client.post().uri("/test/post/").exchange().expectStatus().isAccepted();
    }

}