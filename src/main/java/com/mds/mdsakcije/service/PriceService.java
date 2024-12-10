package com.mds.mdsakcije.service;

import com.mds.mdsakcije.domain.Price;
import com.mds.mdsakcije.domain.PriceId;
import com.mds.mdsakcije.dto.BuyDTO;
import com.mds.mdsakcije.dto.BuyMultipleResponseDTO;
import com.mds.mdsakcije.dto.BuyResponseDTO;
import com.mds.mdsakcije.dto.PriceDTO;
import com.mds.mdsakcije.repo.PriceRepo;
import com.mds.mdsakcije.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PriceService {

    private final PriceRepo repo;
    private final StockRepo stockRepo;

    @Autowired
    public PriceService(PriceRepo priceRepository, StockRepo stockRepo) {
        this.repo = priceRepository;
        this.stockRepo = stockRepo;
    }

    public List<Price> getAllPrices() {
        return repo.findAll();
    }

    public Price findById(PriceId id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Price with id: " + id + " not found"));
    }

    public Price save(Price id) {
        return repo.save(id);
    }

    public void deleteById(PriceId id) {
        repo.deleteById(id);
    }

    public HashMap<String,BuyResponseDTO> predlogZaKupovinuJedna(BuyDTO buyDTO) {
        if(!stockRepo.existsById(buyDTO.getOznaka())){
            throw new RuntimeException("Ne postoji akcija sa oznakom: "+buyDTO.getOznaka());
        }
        HashMap<String,BuyResponseDTO> responseDTOHashMap = new HashMap<>();
        responseDTOHashMap.put("Trazeni period "
                +buyDTO.getOdDatuma().toString()
                +" - "+buyDTO.getDoDatuma().toString()
                +" :",
                predlogJedna(buyDTO.getOznaka(),
                buyDTO.getOdDatuma(),
                buyDTO.getDoDatuma()));
        responseDTOHashMap.put("Prethodni period "
        +buyDTO.getOdDatuma().minus(DAYS.between(buyDTO.getOdDatuma(),buyDTO.getDoDatuma())+1,DAYS).toString()
        +" - "+buyDTO.getOdDatuma().minusDays(1).toString()
        +" :",
        predlogJedna(buyDTO.getOznaka(),
        buyDTO.getOdDatuma().minus(DAYS.between(buyDTO.getOdDatuma(),buyDTO.getDoDatuma())+1,DAYS),
        buyDTO.getOdDatuma().minusDays(1)));
        responseDTOHashMap.put("Sledeci period "
        +buyDTO.getDoDatuma().plusDays(1).toString()
        +" - "+buyDTO.getDoDatuma().plus(DAYS.between(buyDTO.getOdDatuma(),buyDTO.getDoDatuma())+1,DAYS).toString()
        +" :",
        predlogJedna(buyDTO.getOznaka(),
        buyDTO.getDoDatuma().plusDays(1),
        buyDTO.getDoDatuma().plus(DAYS.between(buyDTO.getOdDatuma(),buyDTO.getDoDatuma())+1,DAYS)));
        return responseDTOHashMap;
    }

    private BuyResponseDTO predlogJedna(String oznaka, LocalDate odDatuma, LocalDate doDatuma) {
        List<Price> prices = repo.findByOznakaAndDatumBetweenOrderByDatumAsc(oznaka, odDatuma, doDatuma);
        if (prices.isEmpty()) {
            return null;
        }
        double diff = -1;
        PriceDTO buyDay = null;
        PriceDTO sellDay = null;
        Price min = prices.get(0);
        for (Price price : prices) {
            if(price.getClose()-min.getClose()>diff){
                diff = price.getClose()-min.getClose();
                sellDay = new PriceDTO(price.getDatum(),price.getClose());
                buyDay = new PriceDTO(min.getDatum(),min.getClose());
            }
            if(price.getClose()<min.getClose()){
                min = price;
            }
        }
        return new BuyResponseDTO(buyDay,sellDay,diff);
    }

    public BuyMultipleResponseDTO predlogZaKupovinuVise(BuyDTO buyDTO) {
        if(!stockRepo.existsById(buyDTO.getOznaka())){
            throw new RuntimeException("Ne postoji akcija sa oznakom: "+buyDTO.getOznaka());
        }
        List<Price> prices = repo.findByOznakaAndDatumBetweenOrderByDatumAsc(buyDTO.getOznaka(), buyDTO.getOdDatuma(), buyDTO.getDoDatuma());
        BuyMultipleResponseDTO responseDTO = new BuyMultipleResponseDTO();
        if (prices.isEmpty()) {
            throw new RuntimeException("Ne postoje podaci za trazeni period");
        }
        for (int i = 0; i < prices.size(); i++) {
            if (i == prices.size() - 1) {
                break;
            }
            if(prices.get(i).getClose()>prices.get(i+1).getClose()){
                continue;
            }
            int j = i+1;
            while(j<prices.size()-1 && prices.get(j).getClose()<prices.get(j+1).getClose()){
                j++;
            }
            responseDTO.getBuyResponseDTOList().add(new BuyResponseDTO(new PriceDTO(prices.get(i).getDatum(),prices.get(i).getClose()),
                    new PriceDTO(prices.get(j).getDatum(),prices.get(j).getClose()),
                    prices.get(j).getClose()-prices.get(i).getClose()));
            i=j;
        }
        responseDTO.setProfit(responseDTO.getBuyResponseDTOList().stream().mapToDouble(BuyResponseDTO::getProfit).sum());
        return responseDTO;
    }
}
