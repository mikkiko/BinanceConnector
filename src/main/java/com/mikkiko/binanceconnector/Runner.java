package com.mikkiko.binanceconnector;

import com.mikkiko.binanceconnector.services.MessageQueue;
import com.mikkiko.binanceconnector.services.PartialBookDepthService;
import com.mikkiko.binanceconnector.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketConnectionManager;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class Runner implements ApplicationRunner {

    private final WebSocketConnectionManager webSocketConnectionManager;
    private final PartialBookDepthService service;
    private final MessageQueue queue;

    @Value("${received.count.messages}")
    private Integer count;

    public Runner(@Qualifier("Binance-WebSocketConnectionManager")
                          WebSocketConnectionManager webSocketConnectionManager,
                  PartialBookDepthService service,
                  MessageQueue queue) {
        this.webSocketConnectionManager = webSocketConnectionManager;
        this.service = service;
        this.queue = queue;
    }

    @Override
    public void run(ApplicationArguments args) {
        webSocketConnectionManager.start();

        int it = 0;
        while (count > 0 || it > 500) {
            String payload = queue.getLastMessage();
            if (Objects.nonNull(payload)) {
                service.savePartialBookDepth(payload);
                count--;
            }
            CommonUtil.waitFor(500, TimeUnit.MILLISECONDS);
            it++;
        }
    }
}
