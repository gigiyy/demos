package com.example.fcb.claim.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimData {

    @JsonProperty("seqNo")
    private String sequenceNumber;
    private String sender;
    private String receiver;
    private String message;

    public ClaimEntity toClaim() {
        return new ClaimEntity(null, sequenceNumber, sender, receiver, message);
    }
}
