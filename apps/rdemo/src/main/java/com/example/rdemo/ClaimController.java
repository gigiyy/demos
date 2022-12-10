package com.example.rdemo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/claims")
public class ClaimController {
   private ClaimRepository claimRepository;

   public ClaimController(ClaimRepository claimRepository) {

      this.claimRepository = claimRepository;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   Mono<Claim> submitClaim(@RequestBody Claim claimData) {
      return claimRepository.save(claimData);
   }
}
