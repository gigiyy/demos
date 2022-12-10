package com.example.fcb.claim.forex;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "forex", url = "${servers.forex.url}")
public interface ForexClient {

    @GetMapping(value = "/forex/{currency}", consumes = "application/json")
    ForexRate getRateFor(@PathVariable String currency);
}
