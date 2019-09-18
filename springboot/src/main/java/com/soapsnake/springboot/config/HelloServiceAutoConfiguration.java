package com.soapsnake.springboot.config;


import com.soapsnake.springboot.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.soapsnake.springboot"})
@ConditionalOnProperty(prefix = "study", name = "enable", havingValue = "true")
public class HelloServiceAutoConfiguration {

	@Bean
	public HelloService helloService() {
		return new HelloService();
	}

}
