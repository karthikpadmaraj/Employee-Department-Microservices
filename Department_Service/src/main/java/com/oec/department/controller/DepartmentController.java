

package com.oec.department.controller;
 
import java.util.List;
 
import jakarta.validation.Valid;
 
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
 
import com.oec.department.entity.Department;

import com.oec.department.service.DepartmentService;
 
@RestController

@RequestMapping("/api/departments")

public class DepartmentController {
 
    private final DepartmentService departmentService;
 
    public DepartmentController(DepartmentService departmentService) {

        this.departmentService = departmentService;

    }
 
    @PostMapping

    public ResponseEntity<Department> createDepartment(

            @Valid @RequestBody Department department) {
 
        Department saved = departmentService.createDepartment(department);
 
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }
 
    @GetMapping

    public ResponseEntity<List<Department>> getAllDepartments() {

        return ResponseEntity.ok(

                departmentService.getAllDepartments());

    }
 
    @GetMapping("/{id}")

    public ResponseEntity<Department> getDepartmentById(

            @PathVariable Long id) {
 
        return ResponseEntity.ok(

                departmentService.getDepartmentById(id));

    }
 
    @PutMapping("/{id}")

    public ResponseEntity<Department> updateDepartment(

            @PathVariable Long id,

            @Valid @RequestBody Department department) {
 
        return ResponseEntity.ok(

                departmentService.updateDepartment(id, department));

    }
 
    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteDepartment(

            @PathVariable Long id) {
 
        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();

    }

}

 