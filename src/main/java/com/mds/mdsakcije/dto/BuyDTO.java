package com.mds.mdsakcije.dto;

import java.time.LocalDate;

public class BuyDTO {
    private String oznaka;
    private LocalDate odDatuma;
    private LocalDate doDatuma;

    public BuyDTO() {
    }

    public BuyDTO(String oznaka, LocalDate odDatuma, LocalDate doDatuma) {
        this.oznaka = oznaka;
        this.odDatuma = odDatuma;
        this.doDatuma = doDatuma;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public LocalDate getOdDatuma() {
        return odDatuma;
    }

    public void setOdDatuma(LocalDate odDatuma) {
        this.odDatuma = odDatuma;
    }

    public LocalDate getDoDatuma() {
        return doDatuma;
    }

    public void setDoDatuma(LocalDate doDatuma) {
        this.doDatuma = doDatuma;
    }
}
