package com.example.rdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Claim {

    private Long id;
    private String sender;
    private String receiver;
    private String message;
}
