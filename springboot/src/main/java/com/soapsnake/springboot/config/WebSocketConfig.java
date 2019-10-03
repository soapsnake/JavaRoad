package com.soapsnake.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.Resource;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Resource
	private StompSessionHandler stompSessionHandler;

	@Value("${wsUrl}")
	private String url;


	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/mywebsockets")
				.setAllowedOrigins("mydomain.com").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config){
		config.enableSimpleBroker("/topic/", "/queue/");
		config.setApplicationDestinationPrefixes("/app");
	}


	@Bean
	public WebSocketStompClient webSocket() {
//		WebSocketClient client = new StandardWebSocketClient();
//		WebSocketStompClient stompClient = new WebSocketStompClient(client);
//		stompClient.connect(url, stompSessionHandler);
		return null;
	}

}
