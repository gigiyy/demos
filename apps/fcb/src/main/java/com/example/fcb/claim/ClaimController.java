package com.example.fcb.claim;

import com.example.fcb.claim.service.Claim;
import com.example.fcb.claim.service.ClaimData;
import com.example.fcb.claim.service.ClaimService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService service;

    public ClaimController(ClaimService service) {
        this.service = service;
    }

    @GetMapping
    ClaimData getClaimData(@RequestParam String seqNo) {
        return service.getClaimdata(seqNo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Claim submitClaim(@RequestBody ClaimData claimData) {
        return service.saveClaimData(claimData);
    }

    @PutMapping("/{id}/send")
    void sendClaim(@PathVariable Long id) {
        service.sendClaim(id);
    }
}
