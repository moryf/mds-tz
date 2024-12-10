package com.mds.mdsakcije.service;

import com.mds.mdsakcije.domain.Stock;
import com.mds.mdsakcije.repo.StockRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockServiceTest {


    @Mock
    private StockRepo stockRepo;

    @InjectMocks
    private StockService stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStocks() {
        List<Stock> mockStocks = new ArrayList<>();
        mockStocks.add(new Stock("TST1", "Test 1", LocalDate.now(), "Test Lokacija 1"));
        mockStocks.add(new Stock("TST2", "Test 2", LocalDate.now(), "Test Lokacija 2"));
        when(stockRepo.findAll()).thenReturn(mockStocks);

        List<Stock> result = stockService.getAllStocks();

        assertEquals(2, result.size());
        assertEquals("TST1", result.get(0).getOznaka());
        verify(stockRepo, times(1)).findAll();
    }

    @Test
    void testFindById_WhenStockExists() {
    Stock mockStock = new Stock("STOCK", "Test Stock", LocalDate.now(), "Test Lokacija");
        when(stockRepo.findById("STOCK")).thenReturn(Optional.of(mockStock));

        Stock result = stockService.findById("STOCK");

        assertNotNull(result);
        assertEquals("STOCK", result.getOznaka());
        verify(stockRepo, times(1)).findById("STOCK");
    }

    @Test
    void testFindById_WhenStockDoesNotExist() {
        when(stockRepo.findById("STOCK1")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> stockService.findById("STOCK1"));
        assertEquals("Stock with oznaka: STOCK1 not found", exception.getMessage());
        verify(stockRepo, times(1)).findById("STOCK1");
    }

    @Test
    void testSave() {
        Stock mockStock = new Stock("STOCK1", "Test Stock", LocalDate.now(), "Test Lokacija");
        when(stockRepo.save(mockStock)).thenReturn(mockStock);

        Stock result = stockService.save(mockStock);

        assertNotNull(result);
        assertEquals("STOCK1", result.getOznaka());
        verify(stockRepo, times(1)).save(mockStock);
    }

    @Test
    void testDeleteById() {
        String oznaka = "STOCK1";
        doNothing().when(stockRepo).deleteById(oznaka);

        stockService.deleteById(oznaka);

        verify(stockRepo, times(1)).deleteById(oznaka);
    }

}
