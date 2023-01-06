package com.example.forex;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

public class ForexRateBase {

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new RateController());
    }
}
