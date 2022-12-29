package com.example.fcb.play.wrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForexClient {

    public ResponseWrapper<ForexResponse> getRate(RequestWrapper<ForexRequest> request) {
        log.info(request.body.getId());
        return new ResponseWrapper<>(ForexResponse.builder().result("OK").build());
    }
}
