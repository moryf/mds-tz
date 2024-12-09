package com.mds.mdsakcije.domain;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class PriceId {
    Stock stock;
    LocalDate datum;
}
