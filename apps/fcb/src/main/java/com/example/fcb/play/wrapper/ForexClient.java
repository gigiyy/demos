package com.example.fcb.play.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForexClient {
    private static Logger logger = LoggerFactory.getLogger(ForexClient.class);

    public ResponseWrapper<ForexResponse> getRate(RequestWrapper<ForexRequest> request) {
        logger.info(request.body.getId());
        return new ResponseWrapper<>(ForexResponse.builder().result("OK").build());
    }
}
