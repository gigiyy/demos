package com.example.fcb.play.number;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_numbers")
public class NumbersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(precision = 11, scale = 2)
    BigDecimal numberA;

    @Digits(integer = 8, fraction = 2)
    BigDecimal numberB;
}
