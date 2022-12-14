package com.example.fcb.claim.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {

    @Id
    @GeneratedValue
    private Long id;
    private String sender;
    private String receiver;
    private String message;

}
