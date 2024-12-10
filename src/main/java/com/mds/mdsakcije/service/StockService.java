package com.mds.mdsakcije.service;

import com.mds.mdsakcije.domain.Stock;
import com.mds.mdsakcije.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockService {
    private final StockRepo stockRepo;

    @Autowired
    public StockService(StockRepo stockRepo) {
        this.stockRepo = stockRepo;
    }

    public List<Stock> getAllStocks() {
        return stockRepo.findAll();
    }

    public Stock findById(String oznaka) {
        return stockRepo.findById(oznaka).orElseThrow(() -> new RuntimeException("Stock with oznaka: " + oznaka + " not found"));
    }

    public Stock save(Stock stock) {
        return stockRepo.save(stock);
    }

    public void deleteById(String oznaka) {
        stockRepo.deleteById(oznaka);
    }
}
