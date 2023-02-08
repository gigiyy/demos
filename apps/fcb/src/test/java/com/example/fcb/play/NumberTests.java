package com.example.fcb.play;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberTests {

    @Test
    public void formatBigDecimal() {
        assertThat(String.format("%.2f", new BigDecimal("20.1"))).isEqualTo("20.10");
        assertThat(String.format("%011.6f", new BigDecimal("33.3"))).isEqualTo("0033.300000");
        assertThat(String.format("%.6f", new BigDecimal("0.01234567"))).isEqualTo("0.012346");
        assertThat(String.format("%.2f", new BigDecimal("33.45678"))).isEqualTo("33.46");
    }
}
