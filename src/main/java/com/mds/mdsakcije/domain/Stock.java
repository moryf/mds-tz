package com.mds.mdsakcije.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    public Stock(String oznaka, String naziv, LocalDate datumNastanka, String lokacija) {
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.datumNastanka = datumNastanka;
        this.lokacija = lokacija;
    }

    public Stock(String oznaka) {
        this.oznaka = oznaka;
    }

    public Stock() {
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDate getDatumNastanka() {
        return datumNastanka;
    }

    public void setDatumNastanka(LocalDate datumNastanka) {
        this.datumNastanka = datumNastanka;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
}
