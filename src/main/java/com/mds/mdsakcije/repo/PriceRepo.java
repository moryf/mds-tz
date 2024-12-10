package com.mds.mdsakcije.repo;

import com.mds.mdsakcije.domain.Price;
import com.mds.mdsakcije.domain.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price, PriceId> {
    @Query("SELECT p FROM Price p WHERE p.stock.oznaka = ?1 AND p.datum BETWEEN ?2 AND ?3 ORDER BY p.datum ASC")
    List<Price> findByOznakaAndDatumBetweenOrderByDatumAsc(String oznaka, LocalDate odDatuma, LocalDate doDatuma);
}
