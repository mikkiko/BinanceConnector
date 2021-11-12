package com.mikkiko.binanceconnector.repositories;

import com.mikkiko.binanceconnector.repositories.entities.PartialBookDepth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartialBookDepthRepo extends JpaRepository<PartialBookDepth, Long> {
}
