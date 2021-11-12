package com.mikkiko.binanceconnector.services;

import com.mikkiko.binanceconnector.repositories.entities.PartialBookDepth;

import java.util.List;

public interface PartialBookDepthService {

    void savePartialBookDepth(String payload);

    List<PartialBookDepth> findAll(Long id);
}
