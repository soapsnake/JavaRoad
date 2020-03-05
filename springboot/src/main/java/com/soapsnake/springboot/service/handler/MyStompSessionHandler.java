package com.soapsnake.springboot.service.handler;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;

@Service(value = "stompSessionHandler")
public class MyStompSessionHandler implements StompSessionHandler {

    @Value("wsTopic")
    private String wstopic;

    @Override
    public void afterConnected(StompSession session, StompHeaders headers) {
        session.subscribe(wstopic, this);
//		session.send("", get)
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] bytes, Throwable throwable) {

    }

    @Override
    public void handleTransportError(StompSession session, Throwable throwable) {

    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return null;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object o) {
        Message message = (Message) o;
        System.out.println("Received : " + message.getPayload() + " from:" + message.getHeaders());
    }
}
