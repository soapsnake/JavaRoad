package com.soapsnake.webflux.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;

import com.soapsnake.webflux.server.pojo.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public Mono<Employee> findEmployeeById(String id) {
        return null;
    }

    @Override
    public Flux<Employee> findAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Flux<Employee> employeeFlux = Flux.fromIterable(employees);
        for (int i = 0; i < 1000; i++) {
            Employee employee = new Employee();
            employee.setName(RandomStringUtils.randomAlphabetic(5));
            employees.add(employee);
        }
        return employeeFlux;
    }

    @Override
    public Mono<Employee> updateEmployee(Employee employee) {
        return null;
    }
}
