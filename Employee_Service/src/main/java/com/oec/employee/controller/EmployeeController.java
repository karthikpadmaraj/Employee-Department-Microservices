package com.oec.employee.controller;
 
import com.oec.employee.entity.Employee;

import com.oec.employee.service.EmployeeService;
 
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController

@RequestMapping("/api/employees")

public class EmployeeController {
 
    private final EmployeeService employeeService;
 
    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;

    }
 
    // Create employee

    @PostMapping

    public ResponseEntity<Employee> createEmployee(

            @RequestBody Employee employee) {
 
        Employee savedEmployee =

                employeeService.createEmployee(employee);
 
        return ResponseEntity.status(HttpStatus.CREATED)

                .body(savedEmployee);

    }
 
    // Get all employees

    @GetMapping

    public ResponseEntity<List<Employee>> getAllEmployees() {
 
        return ResponseEntity.ok(

                employeeService.getAllEmployees());

    }
 
    // Get employee by ID

    @GetMapping("/{id}")

    public ResponseEntity<Employee> getEmployeeById(

            @PathVariable Long id) {
 
        return ResponseEntity.ok(

                employeeService.getEmployeeById(id));

    }
 
    // Get employees by department ID

    @GetMapping("/department/{departmentId}")

    public ResponseEntity<List<Employee>> getEmployeesByDepartment(

            @PathVariable Long departmentId) {
 
        return ResponseEntity.ok(

                employeeService.getEmployeesByDepartment(departmentId));

    }
 
    // Update employee

    @PutMapping("/{id}")

    public ResponseEntity<Employee> updateEmployee(

            @PathVariable Long id,

            @RequestBody Employee employee) {
 
        return ResponseEntity.ok(

                employeeService.updateEmployee(id, employee));

    }
 
    // Delete employee

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteEmployee(

            @PathVariable Long id) {
 
        employeeService.deleteEmployee(id);
 
        return ResponseEntity.noContent().build();

    }

}

 