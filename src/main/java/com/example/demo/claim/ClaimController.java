package com.example.demo.claim;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimRepository claimRepository;

    public ClaimController(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Claim submitClaim(@RequestBody ClaimData claimData) {
        return claimRepository.save(claimData.toClaim());
    }
}
