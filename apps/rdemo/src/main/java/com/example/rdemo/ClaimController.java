package com.example.rdemo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private ClaimRepository claimRepository;

    public ClaimController(ClaimRepository claimRepository) {

        this.claimRepository = claimRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Claim> submitClaim(@Valid @RequestBody Claim claimData) {
        return claimRepository.save(claimData);
    }

    @GetMapping
    Flux<Claim> findAllClaims() {
        return claimRepository.findAll();
    }
}
