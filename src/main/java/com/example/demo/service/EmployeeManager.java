package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.Arrays;
import java.util.List;

public class EmployeeManager {

    public static List<Employee> initializeEmployees() {
        return Arrays.asList(
                new Employee("E001", "Alice", "Johnson", "alice.j@example.com", "Backend Developer"),
                new Employee("E002", "Bob", "Smith", "bob.s@example.com", "DevOps Engineer"),
                new Employee("E003", "Carol", "Williams", "carol.w@example.com", "QA Tester")
        );
    }
}
