package com.mds.mdstzakcije.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mds.mdstzakcije.domain.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
