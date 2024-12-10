package com.mds.mdsakcije.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

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


    public Price(Stock stock, LocalDate datum, double open, double high, double low, double close, double adjClose, long volume) {
        this.stock = stock;
        this.datum = datum;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
    }

    public Price() {
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

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }
}
