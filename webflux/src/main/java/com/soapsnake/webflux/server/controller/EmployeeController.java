package com.soapsnake.webflux.server.controller;


import com.soapsnake.webflux.server.dao.EmployeeRepository;
import com.soapsnake.webflux.server.pojo.Employee;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    // constructor...

    @GetMapping("/{id}")
    private Mono<Employee> getEmployeeById(@PathVariable String id) {
        return employeeRepository.findEmployeeById(id);
    }

    @GetMapping
    private Flux<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @PostMapping("/update")
    private Mono<Employee> updateEmployee(@RequestBody Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }
}
