package com.oec.department.service;
 
import com.oec.department.entity.Department;

import com.oec.department.repository.DepartmentRepository;
 
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
 
import java.util.List;
 
@Service

@Transactional

public class DepartmentService {
 
    private final DepartmentRepository departmentRepository;
 
    public DepartmentService(DepartmentRepository departmentRepository) {

        this.departmentRepository = departmentRepository;

    }
 
    // Create department

    public Department createDepartment(Department department) {
 
        if (departmentRepository.existsByNameIgnoreCase(

                department.getName().trim())) {

            throw new RuntimeException("Department name already exists");

        }
 
        department.setName(department.getName().trim());
 
        return departmentRepository.save(department);

    }
 
    // Get all departments

    @Transactional(readOnly = true)

    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();

    }
 
    // Get department by ID

    @Transactional(readOnly = true)

    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id)

                .orElseThrow(() ->

                    new RuntimeException("Department not found with ID: " + id));

    }
 
    // Update department

    public Department updateDepartment(Long id, Department updatedDepartment) {
 
        Department existingDepartment = getDepartmentById(id);
 
        departmentRepository.findByNameIgnoreCase(

                updatedDepartment.getName().trim()

        ).ifPresent(department -> {

            if (!department.getId().equals(id)) {

                throw new RuntimeException("Department name already exists");

            }

        });
 
        existingDepartment.setName(updatedDepartment.getName().trim());

        existingDepartment.setDescription(updatedDepartment.getDescription());
 
        return departmentRepository.save(existingDepartment);

    }
 
    // Delete department

    public void deleteDepartment(Long id) {

        Department department = getDepartmentById(id);

        departmentRepository.delete(department);

    }

}

 