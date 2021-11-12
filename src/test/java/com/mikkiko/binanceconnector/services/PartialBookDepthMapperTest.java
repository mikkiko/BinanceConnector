package com.mikkiko.binanceconnector.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikkiko.binanceconnector.repositories.entities.Ask;
import com.mikkiko.binanceconnector.repositories.entities.Bid;
import com.mikkiko.binanceconnector.repositories.entities.PartialBookDepth;
import com.mikkiko.binanceconnector.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartialBookDepthMapperTest {

    private static final String path = "/testpayload.json";

    @Test
    public void parseJson() throws IOException {
        PartialBookDepthMapper mapper = new PartialBookDepthMapper(new ObjectMapper());
        String json = CommonUtil.readResourceAsString(path);
        PartialBookDepth actual = mapper.jsonToPartialBookDepth(json);

        PartialBookDepth expected = new PartialBookDepth()
                .setEventType("depthUpdate")
                .setEventTime(1591269996801L)
                .setTransctionTime(1591269996646L)
                .setSymbol("BTCUSD_200626")
                .setPair("BTCUSD")
                .setFirthUpdateId(17276694L)
                .setFinalUpdateId(17276701L)
                .setLastStreamFinalUpdateId(17276678L)
                .setBids(List.of(
                        new Bid().setPriceLevel(9523.0).setQuantity(5),
                        new Bid().setPriceLevel(9522.8).setQuantity(8),
                        new Bid().setPriceLevel(9522.6).setQuantity(2),
                        new Bid().setPriceLevel(9522.4).setQuantity(1),
                        new Bid().setPriceLevel(9522.0).setQuantity(5)
                )).setAsks(List.of(
                        new Ask().setPriceLevel(9524.6).setQuantity(2),
                        new Ask().setPriceLevel(9524.7).setQuantity(3),
                        new Ask().setPriceLevel(9524.9).setQuantity(16),
                        new Ask().setPriceLevel(9525.1).setQuantity(10),
                        new Ask().setPriceLevel(9525.3).setQuantity(6)
                ));
        assertThat(expected).isEqualTo(actual);
    }

}