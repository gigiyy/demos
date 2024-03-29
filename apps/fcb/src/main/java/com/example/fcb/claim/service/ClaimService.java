package com.example.fcb.claim.service;

import com.example.fcb.claim.error.ClaimNotFoundException;
import com.example.fcb.claim.forex.ForexClient;
import com.example.fcb.claim.forex.ForexRate;
import com.example.fcb.request.ClaimRequest;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final ClaimRequest claimRequest;

    private final ForexClient forexClient;

    public ClaimService(ClaimRepository claimRepository, ClaimRequest claimRequest,
        ForexClient forexClient) {
        this.claimRepository = claimRepository;
        this.claimRequest = claimRequest;
        this.forexClient = forexClient;
    }

    public ClaimData getClaimdata(String seqNo) {
        ForexRate rate = forexClient.getRateFor("eur");
        String message =
            "Currency: " + rate.getCurrency().toUpperCase() + ", Rate: " + rate.getToTwd();
        return ClaimData.builder().sender("FCB").receiver("MIZUHO").message(message).build();
    }

    public ClaimEntity saveClaimData(ClaimData claimData) {
        return claimRepository.save(claimData.toClaim());
    }

    public void sendClaim(Long id) {
        claimRepository.findById(id)
            .ifPresentOrElse(claimRequest::send, () -> {
                    throw new ClaimNotFoundException(
                        String.format("Claim message with id %s not found", id));
                }
            );
    }
}
