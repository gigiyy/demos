package com.example.forex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate {
   private String currency;
   private BigDecimal toUsd;
   private BigDecimal toTwd;
}
