package com.example.forex;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {

    @GetMapping("/forex/{currency}")
    public Rate getRate(@PathVariable String currency) {
        if ("EUR".equalsIgnoreCase(currency)) {
            return Rate.builder()
                .currency("EUR")
                .toUsd(new BigDecimal("1.40"))
                .toTwd(new BigDecimal("40.3"))
                .build();
        } else {
            throw new ForexRateNotFoundException(currency + " not found in the system");
        }
    }
}
