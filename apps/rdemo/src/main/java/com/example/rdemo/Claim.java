package com.example.rdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Claim {

    @Id
    private Long id;
    private String sender;
    private String receiver;
    private String message;
}
