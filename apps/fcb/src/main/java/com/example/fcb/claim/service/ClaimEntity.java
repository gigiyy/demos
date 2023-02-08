package com.example.fcb.claim.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "sequence_number")
    private String sequenceNumber;
    private String sender;
    private String receiver;
    private String message;

}
