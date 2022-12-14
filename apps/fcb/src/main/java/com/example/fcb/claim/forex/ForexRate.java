package com.example.fcb.claim.forex;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForexRate {

    private String currency;
    private BigDecimal toTwd;
    private BigDecimal toUsd;
}
