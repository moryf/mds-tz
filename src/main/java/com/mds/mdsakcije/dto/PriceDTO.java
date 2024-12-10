package com.mds.mdsakcije.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class PriceDTO {
    private LocalDate datum;
    private double cenaZatvaranja;

    public PriceDTO() {
    }

    public PriceDTO(LocalDate datum, double cenaZatvaranja) {
        this.datum = datum;
        this.cenaZatvaranja = cenaZatvaranja;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getCenaZatvaranja() {
        return cenaZatvaranja;
    }

    public void setCenaZatvaranja(double cenaZatvaranja) {
        this.cenaZatvaranja = cenaZatvaranja;
    }
}
