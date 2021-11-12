package com.mikkiko.binanceconnector.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@Service("CustomWebSocketHandler")
@Slf4j
public class CustomWebSocketHandler extends TextWebSocketHandler implements MessageQueue {

    private final PayloadProvider payloadProvider;
    private final Queue<TextMessage> messageQueue;

    public CustomWebSocketHandler(PayloadProvider payloadProvider) {
        this.payloadProvider = payloadProvider;
        this.messageQueue = new LinkedList<>();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("Message Received:\n" + message.getPayload());
        messageQueue.add(message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connected.");
        String payload = payloadProvider.getPayload();
        session.sendMessage(new TextMessage(payload));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("Error", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("Connection is closed by cause: {}", status.getReason());
    }

    public String getLastMessage() {
        TextMessage message = messageQueue.poll();
        if (Objects.nonNull(message))
            return message.getPayload();
        else
            return null;
    }

    public Integer queueSize() {
        return messageQueue.size();
    }
}
