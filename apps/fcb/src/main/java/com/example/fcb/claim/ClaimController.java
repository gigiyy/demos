package com.example.fcb.claim;

import com.example.fcb.request.ClaimRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimRepository claimRepository;
    private final ClaimRequest claimRequest;

    private final ForexClient forexClient;

    public ClaimController(ClaimRepository claimRepository, ClaimRequest claimRequest, ForexClient forexClient) {
        this.claimRepository = claimRepository;
        this.claimRequest = claimRequest;
        this.forexClient = forexClient;
    }

    @GetMapping
    ClaimData getClaimData(@RequestParam String seqNo) {
        ForexRate rate = forexClient.getRateFor("eur");
        String message = "Currency: " + rate.getCurrency().toUpperCase() + ", Rate: " + rate.getToTwd();
        return ClaimData.builder().sender("FCB").receiver("MIZUHO").message(message).build();
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
