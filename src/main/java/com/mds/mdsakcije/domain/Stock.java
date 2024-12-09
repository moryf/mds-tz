package com.mds.mdsakcije.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Stock {
    @Id
    @Column(length = 5)
    String oznaka;
    @Column(nullable = false)
    String naziv;
    @Column(nullable = false)
    LocalDate datumNastanka;
    @Column(nullable = false)
    String lokacija;
}
