package com.example.fcb.claim;

import com.example.fcb.claim.service.ClaimEntity;
import com.example.fcb.claim.service.ClaimData;
import com.example.fcb.claim.service.ClaimService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    ClaimEntity submitClaim(@RequestBody ClaimData claimData) {
        return service.saveClaimData(claimData);
    }

    @PutMapping("/{id}/send")
    void sendClaim(@PathVariable Long id) {
        service.sendClaim(id);
    }
}
