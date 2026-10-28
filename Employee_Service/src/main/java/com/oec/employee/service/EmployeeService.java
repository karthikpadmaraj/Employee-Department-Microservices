

package com.oec.employee.service;
 
import java.util.List;
 
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
 
import com.oec.employee.client.DepartmentClient;

import com.oec.employee.entity.Employee;

import com.oec.employee.exception.DuplicateResourceException;

import com.oec.employee.exception.ResourceNotFoundException;

import com.oec.employee.repository.EmployeeRepository;
 
@Service

@Transactional

public class EmployeeService {
 
    private final EmployeeRepository employeeRepository;

    private final DepartmentClient departmentClient;
 
    public EmployeeService(EmployeeRepository employeeRepository,

                           DepartmentClient departmentClient) {

        this.employeeRepository = employeeRepository;

        this.departmentClient = departmentClient;

    }
 
    public Employee createEmployee(Employee employee) {
 
        departmentClient.validateDepartment(employee.getDepartmentId());
 
        String email = employee.getEmail().trim().toLowerCase();
 
        if (employeeRepository.existsByEmailIgnoreCase(email)) {

            throw new DuplicateResourceException(

                    "Employee email already exists: " + email);

        }
 
        employee.setFirstName(employee.getFirstName().trim());

        employee.setLastName(employee.getLastName().trim());

        employee.setEmail(email);
 
        return employeeRepository.save(employee);

    }
 
    @Transactional(readOnly = true)

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();

    }
 
    @Transactional(readOnly = true)

    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException(

                        "Employee not found with ID: " + id));

    }
 
    @Transactional(readOnly = true)

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
 
        departmentClient.validateDepartment(departmentId);
 
        return employeeRepository.findByDepartmentId(departmentId);

    }
 
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
 
        Employee existing = getEmployeeById(id);
 
        departmentClient.validateDepartment(

                updatedEmployee.getDepartmentId());
 
        String email = updatedEmployee.getEmail().trim().toLowerCase();
 
        employeeRepository.findByEmailIgnoreCase(email)

                .ifPresent(found -> {

                    if (!found.getId().equals(id)) {

                        throw new DuplicateResourceException(

                                "Employee email already exists: " + email);

                    }

                });
 
        existing.setFirstName(updatedEmployee.getFirstName().trim());

        existing.setLastName(updatedEmployee.getLastName().trim());

        existing.setEmail(email);

        existing.setPhone(updatedEmployee.getPhone());

        existing.setDepartmentId(updatedEmployee.getDepartmentId());
 
        return employeeRepository.save(existing);

    }
 
    public void deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);

    }

}

 