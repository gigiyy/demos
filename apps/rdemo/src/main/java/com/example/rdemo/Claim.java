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
    @Size(min = 4)
    private String sender;
    @Size(min = 4)
    private String receiver;
    @NotEmpty
    private String message;
}
