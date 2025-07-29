package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.Employees;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Employees employees;

    public EmployeeController(Employees employees) {
        this.employees = employees;
    }

    @GetMapping
    public Map<String, List<Employee>> getAllEmployees() {
        return Map.of("Employees", employees.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<Map<String, List<Employee>>> addEmployee(@RequestBody Employee newEmployee) {
        employees.addEmployee(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("Employees", employees.getAllEmployees()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updated) {
        Employee result = employees.updateEmployee(id, updated);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employees.deleteEmployee(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
