package com.example.fcb.claim.forex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForexRate {
    private String currency;
    private BigDecimal toTwd;
    private BigDecimal toUsd;
}
