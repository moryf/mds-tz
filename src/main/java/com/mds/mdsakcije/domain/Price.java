package com.mds.mdsakcije.domain;

import jakarta.persistence.*;
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
@IdClass(PriceId.class)
public class Price {
    @ManyToOne
    @Id
    Stock stock;
    @Column(name = "Date")
    @Id
    LocalDate datum;
    @Column(name = "Open", nullable = false)

    double open;
    @Column(name = "High", nullable = false)
    double high;
    @Column(name = "Low", nullable = false)
    double low;
    @Column(name = "Close", nullable = false)
    double close;
    @Column(name = "AdjClose", nullable = false)
    double adjClose;
    @Column(name = "Volume", nullable = false)
    long volume;
}
