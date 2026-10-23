package com.oec.employee.entity;
 
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.Table;
 
@Entity

@Table(name = "employees")

public class Employee {
 
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
 
    @Column(nullable = false, length = 80)

    private String firstName;
 
    @Column(nullable = false, length = 80)

    private String lastName;
 
    @Column(nullable = false, unique = true, length = 150)

    private String email;
 
    @Column(length = 20)

    private String phone;
 
    @Column(nullable = false)

    private Long departmentId;
 
    public Employee() {

    }
 
    public Employee(String firstName, String lastName,

                    String email, String phone, Long departmentId) {

        this.firstName = firstName;

        this.lastName = lastName;

        this.email = email;

        this.phone = phone;

        this.departmentId = departmentId;

    }
 
    public Long getId() {

        return id;

    }
 
    public void setId(Long id) {

        this.id = id;

    }
 
    public String getFirstName() {

        return firstName;

    }
 
    public void setFirstName(String firstName) {

        this.firstName = firstName;

    }
 
    public String getLastName() {

        return lastName;

    }
 
    public void setLastName(String lastName) {

        this.lastName = lastName;

    }
 
    public String getEmail() {

        return email;

    }
 
    public void setEmail(String email) {

        this.email = email;

    }
 
    public String getPhone() {

        return phone;

    }
 
    public void setPhone(String phone) {

        this.phone = phone;

    }
 
    public Long getDepartmentId() {

        return departmentId;

    }
 
    public void setDepartmentId(Long departmentId) {

        this.departmentId = departmentId;

    }

}

 