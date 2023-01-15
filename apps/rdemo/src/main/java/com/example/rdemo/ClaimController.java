package com.example.rdemo;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    Mono<Map<String, String>> handleValidationException(WebExchangeBindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return Mono.just(errors);
    }
}
