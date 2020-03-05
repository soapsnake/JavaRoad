package com.soapsnake.webflux.server.dao;

import com.soapsnake.webflux.server.pojo.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    Mono<Employee> findEmployeeById(String id);

    Flux<Employee> findAllEmployees();

    Mono<Employee> updateEmployee(Employee employee);
}
