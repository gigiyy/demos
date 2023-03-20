package com.example.fcb.claim.forex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
//@AutoConfigureStubRunner(ids = "com.example:forex:+:stubs:8082", stubsMode = StubsMode.LOCAL)
@AutoConfigureWireMock(port = 8082)
public class ForexClientTests {

    @Autowired
    ForexClient forexClient;

    @Test
    public void getForexRate_shouldReturnRates() {
        ForexRate rate = forexClient.getRateFor("usd");
        assertThat(rate.getToTwd()).isEqualTo(new BigDecimal("40.3"));
    }

}
