package com.example.forex;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate {

    private String currency;
    private BigDecimal toUsd;
    private BigDecimal toTwd;
}
