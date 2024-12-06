package com.mds.mdstzakcije.repo;

import com.mds.mdstzakcije.domain.Price;
import com.mds.mdstzakcije.domain.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {
}
