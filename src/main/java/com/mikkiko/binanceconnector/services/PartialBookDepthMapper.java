package com.mikkiko.binanceconnector.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikkiko.binanceconnector.repositories.entities.Ask;
import com.mikkiko.binanceconnector.repositories.entities.Bid;
import com.mikkiko.binanceconnector.repositories.entities.PartialBookDepth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartialBookDepthMapper {

    private final ObjectMapper objectMapper;

    public PartialBookDepth jsonToPartialBookDepth(String payload) throws IOException {
        JsonNode root = objectMapper.readTree(payload);
        PartialBookDepth depth = new PartialBookDepth();

        depth.setEventType(root.get("e").asText());
        depth.setEventTime(root.get("E").asLong());
        depth.setTransctionTime(root.get("T").asLong());
        depth.setSymbol(root.get("s").asText());
        depth.setPair(root.get("ps").asText());
        depth.setFirthUpdateId(root.get("U").asLong());
        depth.setFinalUpdateId(root.get("u").asLong());
        depth.setLastStreamFinalUpdateId(root.get("pu").asLong());

        List<Bid> bids = new ArrayList<>();
        for (JsonNode b : root.get("b")) {
            bids.add(new Bid()
                    .setPriceLevel(b.get(0).asDouble())
                    .setQuantity(b.get(1).asInt())
            );
        }
        depth.setBids(bids);

        List<Ask> asks = new ArrayList<>();
        for (JsonNode a : root.get("a")) {
            asks.add(new Ask()
                    .setPriceLevel(a.get(0).asDouble())
                    .setQuantity(a.get(1).asInt())
            );
        }
        depth.setAsks(asks);

        return depth;
    }
}