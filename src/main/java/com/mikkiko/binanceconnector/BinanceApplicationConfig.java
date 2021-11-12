package com.mikkiko.binanceconnector;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class BinanceApplicationConfig {

    @Bean
    public WebSocketClient webSocketClient(){
        return new StandardWebSocketClient();
    }

    @Bean("Binance-WebSocketConnectionManager")
    public WebSocketConnectionManager webSocketConnectionManager(WebSocketClient client,
                                                                 @Qualifier("CustomWebSocketHandler")
                                                                 WebSocketHandler handler,
                                                                 @Value("${binance.websocket.stream.url}")String url){
        return new WebSocketConnectionManager(client, handler, url);
    }
}
