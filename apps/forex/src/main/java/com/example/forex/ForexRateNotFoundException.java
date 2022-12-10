package com.example.forex;

public class ForexRateNotFoundException extends RuntimeException {
    public ForexRateNotFoundException(String msg) {
        super(msg);
    }
}
