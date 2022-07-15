package com.example.demo.claim;

import com.example.demo.request.ClaimRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimRepository claimRepository;
    private final ClaimRequest claimRequest;

    public ClaimController(ClaimRepository claimRepository, ClaimRequest claimRequest) {
        this.claimRepository = claimRepository;
        this.claimRequest = claimRequest;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Claim submitClaim(@RequestBody ClaimData claimData) {
        return claimRepository.save(claimData.toClaim());
    }

    @PutMapping("/{id}/send")
    void sendClaim(@PathVariable Long id) {
        Optional<Claim> found = claimRepository.findById(id);
        if (found.isEmpty()) {
            throw new ClaimNotFoundException(String.format("Claim message with id %s not found", id));
        }
        found.map((Claim claim) -> {
            claimRequest.send(claim);
            return null;
        });
    }
}
