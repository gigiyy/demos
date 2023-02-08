package com.example.fcb.request;

import com.example.fcb.claim.service.ClaimEntity;
import org.springframework.stereotype.Service;

@Service
public class ClaimRequest {

    public SendResult send(ClaimEntity claim) {
        return new SendResult();
    }
}
