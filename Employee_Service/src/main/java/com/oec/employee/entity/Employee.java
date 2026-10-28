

package com.oec.employee.entity;
 
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Positive;

import jakarta.validation.constraints.Pattern;

import jakarta.validation.constraints.Size;
 
@Entity

@Table(name = "employees")

public class Employee {
 
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
 
    @NotBlank(message = "First name is required")

    @Size(max = 80, message = "First name must not exceed 80 characters")

    @Column(nullable = false, length = 80)

    private String firstName;
 
    @NotBlank(message = "Last name is required")

    @Size(max = 80, message = "Last name must not exceed 80 characters")

    @Column(nullable = false, length = 80)

    private String lastName;
 
    @NotBlank(message = "Email is required")

    @Email(message = "Enter a valid email address")

    @Size(max = 150, message = "Email must not exceed 150 characters")

    @Column(nullable = false, unique = true, length = 150)

    private String email;
 
    @Pattern(

        regexp = "^[0-9+() -]{7,20}$",

        message = "Phone must contain 7-20 valid phone characters"

    )

    @Column(length = 20)

    private String phone;
 
    @NotNull(message = "Department ID is required")

    @Positive(message = "Department ID must be positive")

    @Column(nullable = false)

    private Long departmentId;
 
    public Employee() {

    }
 
    public Employee(String firstName, String lastName, String email,

                    String phone, Long departmentId) {

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

 