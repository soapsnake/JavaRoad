package com.soapsnake.webflux.clientside;

import com.soapsnake.webflux.server.pojo.Employee;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 用这个模拟前端调用后端接口
 */
public class EmployeeWebClient {


	WebClient client = WebClient.create("http://localhost:8080");

	// ...

	public void testGetOne() {
		Mono<Employee> employeeMono = client.get()
				.uri("/employees/{id}", "1")
				.retrieve()
				.bodyToMono(Employee.class);

		employeeMono.subscribe(System.out::println);
	}

	public void testGetAll() {
		Flux<Employee> employeeFlux = client.get()
				.uri("/employees")
				.retrieve()
				.bodyToFlux(Employee.class);

		employeeFlux.subscribe(System.out::println);
	}


	public static void main(String[] args) {
		EmployeeWebClient employeeWebClient = new EmployeeWebClient();

		employeeWebClient.testGetOne();


		employeeWebClient.testGetAll();
	}



}
