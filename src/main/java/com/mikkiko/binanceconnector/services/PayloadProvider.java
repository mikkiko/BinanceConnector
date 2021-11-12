package com.mikkiko.binanceconnector.services;

import com.mikkiko.binanceconnector.util.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PayloadProvider {

    @Value("${payload.path}")
    private Resource resource;

    public String getPayload() {
        try {
            return CommonUtil.readResourceAsString(resource);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while providing payload", e);
        }
    }
}
