package com.example.crud_redis_nnt.controller;


import com.example.crud_redis_nnt.model.Employee;
import com.example.crud_redis_nnt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        employeeRepository.saveEmployee(employee);
        return employee;
    }

    @GetMapping("/employees")
    public Set<Employee> findAll(){

        return employeeRepository.findAll();
    }
    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable("id") Integer id){

        return employeeRepository.findById(id);
    }

    @PutMapping("/employee/{id}")
    public void update(@RequestBody Employee employee, @PathVariable("id") int id){
        employeeRepository.delete(id);
        employeeRepository.update(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable("id") Integer id){
        employeeRepository.delete(id);
    }

}
