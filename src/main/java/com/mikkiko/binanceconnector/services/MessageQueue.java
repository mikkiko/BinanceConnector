package com.mikkiko.binanceconnector.services;

import org.springframework.web.socket.TextMessage;

public interface MessageQueue {

    String getLastMessage();

    Integer queueSize();
}
