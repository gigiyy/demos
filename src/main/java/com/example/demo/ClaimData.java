package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimData {
    private String sender;
    private String receiver;
    private String message;

    public Claim toClaim() {
        return new Claim(null, sender, receiver, message);
    }
}
