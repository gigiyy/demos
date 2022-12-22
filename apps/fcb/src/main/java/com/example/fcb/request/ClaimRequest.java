package com.example.fcb.request;

import com.example.fcb.claim.service.Claim;
import org.springframework.stereotype.Service;

@Service
public class ClaimRequest {

    public SendResult send(Claim claim) {
        return new SendResult();
    }
}
