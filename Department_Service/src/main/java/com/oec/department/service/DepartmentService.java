

package com.oec.department.service;
 
import java.util.List;
 
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
 
import com.oec.department.entity.Department;

import com.oec.department.exception.DuplicateResourceException;

import com.oec.department.exception.ResourceNotFoundException;

import com.oec.department.repository.DepartmentRepository;
 
@Service

@Transactional

public class DepartmentService {
 
    private final DepartmentRepository departmentRepository;
 
    public DepartmentService(DepartmentRepository departmentRepository) {

        this.departmentRepository = departmentRepository;

    }
 
    public Department createDepartment(Department department) {
 
        String name = department.getName().trim();
 
        if (departmentRepository.existsByNameIgnoreCase(name)) {

            throw new DuplicateResourceException(

                    "Department name already exists: " + name);

        }
 
        department.setName(name);

        return departmentRepository.save(department);

    }
 
    @Transactional(readOnly = true)

    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();

    }
 
    @Transactional(readOnly = true)

    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException(

                        "Department not found with ID: " + id));

    }
 
    public Department updateDepartment(

            Long id, Department updatedDepartment) {
 
        Department existing = getDepartmentById(id);

        String name = updatedDepartment.getName().trim();
 
        departmentRepository.findByNameIgnoreCase(name)

                .ifPresent(found -> {

                    if (!found.getId().equals(id)) {

                        throw new DuplicateResourceException(

                                "Department name already exists: " + name);

                    }

                });
 
        existing.setName(name);

        existing.setDescription(updatedDepartment.getDescription());
 
        return departmentRepository.save(existing);

    }
 
    public void deleteDepartment(Long id) {

        Department department = getDepartmentById(id);

        departmentRepository.delete(department);

    }

}

 