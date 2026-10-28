

package com.oec.employee.controller;
 
import java.util.List;
 
import jakarta.validation.Valid;
 
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
 
import com.oec.employee.entity.Employee;

import com.oec.employee.service.EmployeeService;
 
@RestController

@RequestMapping("/api/employees")

public class EmployeeController {
 
    private final EmployeeService employeeService;
 
    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;

    }
 
    @PostMapping

    public ResponseEntity<Employee> createEmployee(

            @Valid @RequestBody Employee employee) {
 
        Employee saved = employeeService.createEmployee(employee);
 
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }
 
    @GetMapping

    public ResponseEntity<List<Employee>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployees());

    }
 
    @GetMapping("/{id}")

    public ResponseEntity<Employee> getEmployeeById(

            @PathVariable Long id) {

        return ResponseEntity.ok(employeeService.getEmployeeById(id));

    }
 
    @GetMapping("/department/{departmentId}")

    public ResponseEntity<List<Employee>> getEmployeesByDepartment(

            @PathVariable Long departmentId) {

        return ResponseEntity.ok(

                employeeService.getEmployeesByDepartment(departmentId));

    }
 
    @PutMapping("/{id}")

    public ResponseEntity<Employee> updateEmployee(

            @PathVariable Long id,

            @Valid @RequestBody Employee employee) {
 
        return ResponseEntity.ok(

                employeeService.updateEmployee(id, employee));

    }
 
    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteEmployee(

            @PathVariable Long id) {
 
        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();

    }

}

 