package com.mds.mdsakcije.controller;

import com.mds.mdsakcije.domain.Stock;
import com.mds.mdsakcije.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(stockService.getAllStocks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri dohvatanju svih akcija");
        }
    }

    @GetMapping("/{oznaka}")
    public ResponseEntity<?> getStockByOznaka(@PathVariable String oznaka){
        try {
            return ResponseEntity.ok(stockService.findById(oznaka));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri dohvatanju akcije sa oznakom: " + oznaka);
        }
    }

    @PostMapping("/dodaj")
    public ResponseEntity<?> dodajAkciju(@RequestBody Stock stock){
        try {
            return ResponseEntity.ok(stockService.save(stock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri dodavanju akcije");
        }
    }

    @PutMapping("/izmeni")
    public ResponseEntity<?> izmeniAkciju(@RequestBody Stock stock){
        try {
            return ResponseEntity.ok(stockService.save(stock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri izmeni akcije");
        }
    }

    @DeleteMapping("/obrisi/{oznaka}")
    public ResponseEntity<?> obrisiAkciju(@PathVariable String oznaka){
        try {
            stockService.deleteById(oznaka);
            return ResponseEntity.ok("Uspesno obrisana akcija sa oznakom: " + oznaka);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri brisanju akcije sa oznakom: " + oznaka);
        }
    }

}
