package com.mds.mdstzakcije.service;

import com.mds.mdstzakcije.repo.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository repository;
}
