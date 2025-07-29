package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;
import com.example.demo.exception.EmployeeNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Employees {

    private final List<Employee> employeeList = new ArrayList<>(EmployeeManager.initializeEmployees());

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public synchronized void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
    public synchronized Employee updateEmployee(String id, Employee updated) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getEmployee_id().equals(id)) {
                // enforce id consistency
                updated.setEmployee_id(id);
                employeeList.set(i, updated);
                return updated;
            }
        }
        throw new EmployeeNotFoundException(id);
    }

    public synchronized void deleteEmployee(String id) {
        boolean removed = employeeList.removeIf(e -> id.equals(e.getEmployee_id()));
        if (!removed) throw new EmployeeNotFoundException(id);
    }

}
