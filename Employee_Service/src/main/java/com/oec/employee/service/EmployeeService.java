package com.oec.employee.service;
 
import com.oec.employee.client.DepartmentClient;

import com.oec.employee.entity.Employee;

import com.oec.employee.repository.EmployeeRepository;
 
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
 
import java.util.List;
 
@Service

@Transactional

public class EmployeeService {
 
    private final EmployeeRepository employeeRepository;

    private final DepartmentClient departmentClient;
 
    public EmployeeService(

            EmployeeRepository employeeRepository,

            DepartmentClient departmentClient) {
 
        this.employeeRepository = employeeRepository;

        this.departmentClient = departmentClient;

    }
 
    // Create employee

    public Employee createEmployee(Employee employee) {
 
        // Verify that the department exists

        departmentClient.validateDepartment(

                employee.getDepartmentId());
 
        // Check for duplicate email

        String email = employee.getEmail().trim().toLowerCase();
 
        if (employeeRepository.existsByEmailIgnoreCase(email)) {

            throw new RuntimeException(

                    "Employee email already exists");

        }
 
        employee.setFirstName(employee.getFirstName().trim());

        employee.setLastName(employee.getLastName().trim());

        employee.setEmail(email);
 
        return employeeRepository.save(employee);

    }
 
    // Get all employees

    @Transactional(readOnly = true)

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();

    }
 
    // Get employee by ID

    @Transactional(readOnly = true)

    public Employee getEmployeeById(Long id) {
 
        return employeeRepository.findById(id)

                .orElseThrow(() ->

                        new RuntimeException(

                                "Employee not found with ID: " + id));

    }
 
    // Get employees by department ID

    @Transactional(readOnly = true)

    public List<Employee> getEmployeesByDepartment(

            Long departmentId) {
 
        // Verify that the department exists

        departmentClient.validateDepartment(departmentId);
 
        return employeeRepository.findByDepartmentId(departmentId);

    }
 
    // Update employee

    public Employee updateEmployee(

            Long id, Employee updatedEmployee) {
 
        Employee existingEmployee = getEmployeeById(id);
 
        // Verify that the new department exists

        departmentClient.validateDepartment(

                updatedEmployee.getDepartmentId());
 
        String email = updatedEmployee.getEmail()

                .trim().toLowerCase();
 
        // Check whether another employee uses this email

        employeeRepository.findByEmailIgnoreCase(email)

                .ifPresent(employee -> {

                    if (!employee.getId().equals(id)) {

                        throw new RuntimeException(

                                "Employee email already exists");

                    }

                });
 
        existingEmployee.setFirstName(

                updatedEmployee.getFirstName().trim());
 
        existingEmployee.setLastName(

                updatedEmployee.getLastName().trim());
 
        existingEmployee.setEmail(email);

        existingEmployee.setPhone(updatedEmployee.getPhone());
 
        existingEmployee.setDepartmentId(

                updatedEmployee.getDepartmentId());
 
        return employeeRepository.save(existingEmployee);

    }
 
    // Delete employee

    public void deleteEmployee(Long id) {
 
        Employee employee = getEmployeeById(id);
 
        employeeRepository.delete(employee);

    }

}

 