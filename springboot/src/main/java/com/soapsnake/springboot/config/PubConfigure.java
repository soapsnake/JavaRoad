package com.soapsnake.springboot.config;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubConfigure {

	@Value("${subscribeKey}")
	private String subscribeKey;

	@Value("${publishKey}")
	private String publishKey;

	@Bean
	public PubNub getConfig() {
		PNConfiguration pnConfiguration =  new PNConfiguration();
		pnConfiguration.setSubscribeKey(subscribeKey);
		pnConfiguration.setPublishKey(publishKey);
		return new PubNub(pnConfiguration);
	}
}
