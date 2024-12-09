package com.mds.mdsakcije.repo;

import com.mds.mdsakcije.domain.Price;
import com.mds.mdsakcije.domain.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends JpaRepository<Price, PriceId> {
}
