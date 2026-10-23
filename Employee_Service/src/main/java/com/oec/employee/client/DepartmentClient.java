

package com.oec.employee.client;
 
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import org.springframework.web.client.RestClient;
 
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
 
        restClient.get()

                .uri("/api/departments/{id}", departmentId)

                .retrieve()

                .toBodilessEntity();
    }
}
