package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.service.Employees;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Employees employees;

    @Test
    void testInitialEmployeeListHas3Employees() {
        List<Employee> list = employees.getAllEmployees();
        assertEquals(3, list.size(), "Initial employee list should have 3 entries.");
    }

    @Test
    void testAddEmployeeViaService() {
        Employee newEmp = new Employee("E004", "David", "Lee", "david@example.com", "Product Manager");
        employees.addEmployee(newEmp);

        List<Employee> list = employees.getAllEmployees();
        assertTrue(list.contains(newEmp), "Newly added employee should be in the list.");
        assertEquals(4, list.size(), "List size should increase after adding an employee.");
    }

    @Test
    void testGetEmployeesAPI() {
        ResponseEntity<Map> response = restTemplate.getForEntity("/employees", Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null.");
        assertTrue(response.getBody().containsKey("Employees"), "Response should contain 'Employees' key.");
    }

    @Test
    void testPostEmployeeAPI() {
        Employee newEmp = new Employee("E005", "Eva", "Chen", "eva@example.com", "UX Designer");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Employee> request = new HttpEntity<>(newEmp, headers);

        ResponseEntity<Map> postResponse = restTemplate.postForEntity("/employees", request, Map.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        ResponseEntity<Map> getResponse = restTemplate.getForEntity("/employees", Map.class);
        Map body = getResponse.getBody();
        assertNotNull(body, "GET response body should not be null.");

        List<Map<String, String>> list = (List<Map<String, String>>) body.get("Employees");
        assertNotNull(list, "'Employees' list should not be null.");

        boolean found = list.stream().anyMatch(emp -> "E005".equals(emp.get("employee_id")));
        assertTrue(found, "POSTed employee should exist in the retrieved list.");
    }
}
