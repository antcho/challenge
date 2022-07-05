package com.anthony.backend.service;

import com.anthony.backend.model.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private List<Company> companies;

    public CompanyService() {
        this.companies = new ArrayList<>();
    }

    public Company getCompanyById(int companyId) {
        return companies.stream()
                .filter(c -> c.getId() == companyId)
                .findFirst().get();
    }

    public void addCompany(Company company) {
        companies.add(company);
    }
}
