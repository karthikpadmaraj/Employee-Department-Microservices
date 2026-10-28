

package com.oec.employee.client;
 
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.client.ResourceAccessException;

import org.springframework.web.client.RestClient;
 
import com.oec.employee.exception.DepartmentServiceUnavailableException;

import com.oec.employee.exception.ResourceNotFoundException;
 
@Component

public class DepartmentClient {
 
    private final RestClient restClient;
 
    public DepartmentClient(

            @Value("${department.service.url}") String baseUrl) {

        this.restClient = RestClient.builder()

                .baseUrl(baseUrl)

                .build();

    }
 
    public void validateDepartment(Long departmentId) {

        try {

            restClient.get()

                    .uri("/api/departments/{id}", departmentId)

                    .retrieve()

                    .toBodilessEntity();
 
        } catch (HttpClientErrorException.NotFound ex) {

            throw new ResourceNotFoundException(

                    "Department not found with ID: " + departmentId);
 
        } catch (ResourceAccessException ex) {

            throw new DepartmentServiceUnavailableException(

                    "Cannot connect to Department Service. "

                    + "Check whether port 8081 is running.");
 
        } catch (HttpClientErrorException ex) {

            throw new DepartmentServiceUnavailableException(

                    "Department Service returned HTTP "

                    + ex.getStatusCode().value());

        }

    }

}

 