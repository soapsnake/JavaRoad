package com.soapsnake.springboot.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class WebSocketService {

    @Value("${wsUrl}")
    private String url;

    @Resource
    private WebSocketStompClient stompClient;

    @Resource
    private StompSessionHandler stompSessionHandler;

    @PostConstruct
    public void getMsg() {
//		try {
//			StompSession session = stompClient.connect(url, stompSessionHandler).get();
//		} catch (InterruptedException | ExecutionException e) {
//			System.out.println("websocket got error " + e);
//			e.printStackTrace();
//		}
    }
}
