package com.mikkiko.binanceconnector.services;

import com.mikkiko.binanceconnector.repositories.PartialBookDepthRepo;
import com.mikkiko.binanceconnector.repositories.entities.PartialBookDepth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartialBookDepthServiceImpl implements PartialBookDepthService {

    private final PartialBookDepthRepo repo;
    private final PartialBookDepthMapper mapper;

    @Override
    public void savePartialBookDepth(String payload) {
        if (Objects.isNull(payload) || payload.contains("error")) {
            log.warn("Can not save: {}", payload);
        } else {
            try {
                PartialBookDepth partialBookDepth = mapper.jsonToPartialBookDepth(payload);
                repo.save(partialBookDepth);
            } catch (IOException e) {
                log.error("Error occurred while saving object to DB", e);
            }
        }
    }

    @Override
    public List<PartialBookDepth> findAll(Long id) {
        return repo.findAll();
    }
}
