package com.mds.mdsakcije.dto;

import java.util.ArrayList;
import java.util.List;

public class BuyMultipleResponseDTO {
    List<BuyResponseDTO> buyResponseDTOList;
    double profit;

    public BuyMultipleResponseDTO() {
        buyResponseDTOList = new ArrayList<>();
        profit = 0;
    }

    public BuyMultipleResponseDTO(List<BuyResponseDTO> buyResponseDTOList, double profit) {
        this.buyResponseDTOList = buyResponseDTOList;
        this.profit = profit;
    }

    public List<BuyResponseDTO> getBuyResponseDTOList() {
        return buyResponseDTOList;
    }

    public void setBuyResponseDTOList(List<BuyResponseDTO> buyResponseDTOList) {
        this.buyResponseDTOList = buyResponseDTOList;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
