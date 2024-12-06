package com.mds.mdstzakcije.service;

import com.mds.mdstzakcije.repo.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository repository;
}
