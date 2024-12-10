package com.mds.mdsakcije.domain;

import java.time.LocalDate;

public class PriceId {
    Stock stock;
    LocalDate datum;

    public PriceId(Stock stock, LocalDate datum) {
        this.stock = stock;
        this.datum = datum;
    }

    public PriceId() {
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
