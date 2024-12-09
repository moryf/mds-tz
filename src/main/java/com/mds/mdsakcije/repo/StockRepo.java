package com.mds.mdsakcije.repo;

import com.mds.mdsakcije.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends JpaRepository<Stock,String> {
}
