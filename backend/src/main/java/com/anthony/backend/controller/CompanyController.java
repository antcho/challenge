package com.anthony.backend.controller;

import com.anthony.backend.model.Company;
import com.anthony.backend.service.CompanyService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int companyId) {
        return new ResponseEntity<>(companyService.getCompanyById(companyId), HttpStatus.OK);
    }
}
