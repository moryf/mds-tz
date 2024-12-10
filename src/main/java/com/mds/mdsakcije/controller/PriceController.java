package com.mds.mdsakcije.controller;

import com.mds.mdsakcije.domain.Price;
import com.mds.mdsakcije.domain.PriceId;
import com.mds.mdsakcije.dto.BuyDTO;
import com.mds.mdsakcije.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/price")
public class PriceController {


    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(priceService.getAllPrices());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri dohvatanju svih cena " + e.getMessage());
        }
    }

    @GetMapping("/id")
    public ResponseEntity<?> getById(@RequestBody PriceId id){
        try {
            return ResponseEntity.ok(priceService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri dohvatanju cene sa id: " + id + " " + e.getMessage());
        }
    }

    @PostMapping("/dodaj")
    public ResponseEntity<?> dodajCenu(@RequestBody Price price){
        try {
            return ResponseEntity.ok(priceService.save(price));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri dodavanju cene " + e.getMessage());
        }
    }

    @PutMapping("/izmeni")
    public ResponseEntity<?> izmeniCenu(@RequestBody Price price){
        try {
            return ResponseEntity.ok(priceService.save(price));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri izmeni cene " + e.getMessage());
        }
    }

    @DeleteMapping("/obrisi")
    public ResponseEntity<?> obrisiCenu(@RequestBody PriceId id){
        try {
            priceService.deleteById(id);
            return ResponseEntity.ok("Uspesno obrisana cena sa id: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri brisanju cene "+ e.getMessage());
        }
    }

    @GetMapping("/predlog-za-kupovinu/jedna")
    public ResponseEntity<?> predlogZaKupovinuJedna(@RequestBody BuyDTO buyDTO){
        try {
            return ResponseEntity.ok(priceService.predlogZaKupovinuJedna(buyDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri dohvatanju predloga za kupovinu " + e.getMessage());
        }
    }

    @GetMapping("/predlog-za-kupovinu/vise")
    public ResponseEntity<?> predlogZaKupovinuVise(@RequestBody BuyDTO buyDTO){
        try {
            return ResponseEntity.ok(priceService.predlogZaKupovinuVise(buyDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greska pri dohvatanju predloga za kupovinu " + e.getMessage());
        }
    }

}
