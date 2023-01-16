package com.example.rdemo;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, getDefaultMessage()));
        return Mono.just(errors);
    }

    private Function<FieldError, String> getDefaultMessage() {
        return error -> {
            String msg = error.getDefaultMessage();
            return msg == null ? "error message not set" : msg;
        };
    }
}
