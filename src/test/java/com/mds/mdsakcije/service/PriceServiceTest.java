package com.mds.mdsakcije.service;

import com.mds.mdsakcije.domain.Price;
import com.mds.mdsakcije.domain.PriceId;
import com.mds.mdsakcije.domain.Stock;
import com.mds.mdsakcije.dto.BuyDTO;
import com.mds.mdsakcije.dto.BuyMultipleResponseDTO;
import com.mds.mdsakcije.dto.BuyResponseDTO;
import com.mds.mdsakcije.repo.PriceRepo;
import com.mds.mdsakcije.repo.StockRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceTest {

    @Mock
    private PriceRepo priceRepo;

    @Mock
    private StockRepo stockRepo;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPrices() {
        List<Price> mockPrices = new ArrayList<>();
        mockPrices.add(new Price(new Stock("STOCK1"), LocalDate.now(), 100, 105, 95, 102, 101, 10000));
        when(priceRepo.findAll()).thenReturn(mockPrices);

        List<Price> result = priceService.getAllPrices();

        assertEquals(1, result.size());
        verify(priceRepo, times(1)).findAll();
    }

    @Test
    void testFindById_WhenPriceExists() {
        PriceId mockId = new PriceId(new Stock("STOCK1"), LocalDate.now());
        Price mockPrice = new Price(mockId.getStock(), mockId.getDatum(), 100, 105, 95, 102, 101, 10000);
        when(priceRepo.findById(mockId)).thenReturn(Optional.of(mockPrice));

        Price result = priceService.findById(mockId);

        assertNotNull(result);
        assertEquals(102, result.getClose());
        verify(priceRepo, times(1)).findById(mockId);
    }

    @Test
    void testFindById_WhenPriceDoesNotExist() {
        PriceId mockId = new PriceId(new Stock("STOCK1"), LocalDate.now());
        when(priceRepo.findById(mockId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> priceService.findById(mockId));
        assertEquals("Price with id: " + mockId + " not found", exception.getMessage());
        verify(priceRepo, times(1)).findById(mockId);
    }

    @Test
    void testSave() {
        Price mockPrice = new Price(new Stock("STOCK1"), LocalDate.now(), 100, 105, 95, 102, 101, 10000);
        when(priceRepo.save(mockPrice)).thenReturn(mockPrice);

        Price result = priceService.save(mockPrice);

        assertNotNull(result);
        assertEquals(102, result.getClose());
        verify(priceRepo, times(1)).save(mockPrice);
    }

    @Test
    void testDeleteById() {
        PriceId mockId = new PriceId(new Stock("STOCK1"), LocalDate.now());
        doNothing().when(priceRepo).deleteById(mockId);

        priceService.deleteById(mockId);

        verify(priceRepo, times(1)).deleteById(mockId);
    }

    @Test
    void testPredlogZaKupovinuJedna_StockDoesNotExist() {
        BuyDTO buyDTO = new BuyDTO("INVALID_STOCK", LocalDate.now().minusDays(10), LocalDate.now());
        when(stockRepo.existsById(buyDTO.getOznaka())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> priceService.predlogZaKupovinuJedna(buyDTO));
        assertEquals("Ne postoji akcija sa oznakom: " + buyDTO.getOznaka(), exception.getMessage());
        verify(stockRepo, times(1)).existsById(buyDTO.getOznaka());
    }

    @Test
    void testPredlogZaKupovinuJedna_ValidStock() {
        BuyDTO buyDTO = new BuyDTO("STOCK1", LocalDate.now().minusDays(10), LocalDate.now());
        when(stockRepo.existsById(buyDTO.getOznaka())).thenReturn(true);
        List<Price> prices = new ArrayList<>();
        prices.add(new Price(new Stock("STOCK1"), LocalDate.now().minusDays(9), 100, 105, 95, 101, 101, 10000));
        prices.add(new Price(new Stock("STOCK1"), LocalDate.now(), 101, 106, 96, 104, 104, 12000));
        when(priceRepo.findByOznakaAndDatumBetweenOrderByDatumAsc("STOCK1", buyDTO.getOdDatuma(), buyDTO.getDoDatuma()))
                .thenReturn(prices);

        HashMap<String, BuyResponseDTO> result = priceService.predlogZaKupovinuJedna(buyDTO);

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(stockRepo, times(1)).existsById(buyDTO.getOznaka());
        verify(priceRepo, times(3)).findByOznakaAndDatumBetweenOrderByDatumAsc(anyString(), any(), any());
    }

    @Test
    void testPredlogZaKupovinuVise_StockDoesNotExist() {
        BuyDTO buyDTO = new BuyDTO("INVALID_STOCK", LocalDate.now().minusDays(10), LocalDate.now());
        when(stockRepo.existsById(buyDTO.getOznaka())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> priceService.predlogZaKupovinuVise(buyDTO));
        assertEquals("Ne postoji akcija sa oznakom: " + buyDTO.getOznaka(), exception.getMessage());
        verify(stockRepo, times(1)).existsById(buyDTO.getOznaka());
    }

    @Test
    void testPredlogZaKupovinuVise_ValidStock() {
        BuyDTO buyDTO = new BuyDTO("STOCK1", LocalDate.now().minusDays(10), LocalDate.now());
        when(stockRepo.existsById(buyDTO.getOznaka())).thenReturn(true);
        List<Price> prices = new ArrayList<>();
        prices.add(new Price(new Stock("STOCK1"), LocalDate.now().minusDays(9), 100, 105, 95, 101, 101, 10000));
        prices.add(new Price(new Stock("STOCK1"), LocalDate.now(), 101, 106, 96, 104, 104, 12000));
        when(priceRepo.findByOznakaAndDatumBetweenOrderByDatumAsc("STOCK1", buyDTO.getOdDatuma(), buyDTO.getDoDatuma()))
                .thenReturn(prices);

        BuyMultipleResponseDTO result = priceService.predlogZaKupovinuVise(buyDTO);

        assertNotNull(result);
        assertEquals(1, result.getBuyResponseDTOList().size());
        verify(stockRepo, times(1)).existsById(buyDTO.getOznaka());
        verify(priceRepo, times(1)).findByOznakaAndDatumBetweenOrderByDatumAsc(buyDTO.getOznaka(), buyDTO.getOdDatuma(), buyDTO.getDoDatuma());
    }
}
