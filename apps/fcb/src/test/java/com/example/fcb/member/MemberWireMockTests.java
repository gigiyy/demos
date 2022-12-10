package com.example.fcb.member;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class MemberWireMockTests {

    @Value("wiremock.server.port")
    int mockServerPort;

    @LocalServerPort
    int port;

    String url;
    WebTestClient client;

    @BeforeAll
    public void setUp() {
        url = "http://localhost:" + port;
        client = WebTestClient.bindToServer().baseUrl(url).build();
    }

    @Test
    public void claimService_should_callMemberEndpoint() {
        stubFor(get(urlEqualTo("/members/find?name")).willReturn(aResponse()
                .withHeader("Content-Type", "text/json").withBody(
                        //language=json
                        """
                                {
                                "id":"100", "name": "Guixin Zhu"
                                }""".stripIndent())));



    }
}
