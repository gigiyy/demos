package com.example.rdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Claim {

    @Id
    private Long id;
    @Size(min = 4, message = "sender is too short")
    private String sender;
    @Size(min = 4, message = "receiver is too short")
    private String receiver;
    @NotEmpty(message = "message can't be empty")
    private String message;
}
