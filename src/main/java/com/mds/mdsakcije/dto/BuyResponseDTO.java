package com.mds.mdsakcije.dto;

public class BuyResponseDTO {
    private PriceDTO buyPrice;
    private PriceDTO sellPrice;
    private double profit;

    public BuyResponseDTO() {
    }

    public BuyResponseDTO(PriceDTO buyPrice, PriceDTO sellPrice, double profit) {
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.profit = profit;
    }

    public PriceDTO getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(PriceDTO buyPrice) {
        this.buyPrice = buyPrice;
    }

    public PriceDTO getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(PriceDTO sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
