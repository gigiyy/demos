package com.example.fcb.claim.service;

import com.example.fcb.claim.service.Claim;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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
