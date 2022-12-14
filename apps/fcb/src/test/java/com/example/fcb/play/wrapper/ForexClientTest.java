package com.example.fcb.play.wrapper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForexClientTest {

    private Logger logger = LoggerFactory.getLogger(ForexClientTest.class);

    @Test
    public void testWrapping() {
        ResponseWrapper<ForexResponse> found = new ForexClient().getRate(
            ForexRequest.builder().id("100").build().wrap());
        logger.info(found.unwrap().getResult());
    }
}
