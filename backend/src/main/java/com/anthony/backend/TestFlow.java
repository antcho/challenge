package com.anthony.backend;

import com.anthony.backend.model.Company;
import com.anthony.backend.model.User;
import com.anthony.backend.service.CompanyService;
import com.anthony.backend.service.UserService;
import lombok.Data;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Data
public class TestFlow {
    private final UserService userService;
    private final CompanyService companyService;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        System.out.println("START");

        User user1 = new User(1, "John");
        User user2 = new User(2, "Jessica");
        userService.addUser(user1);
        userService.addUser(user2);

        Company company1 = new Company(1, "Tesla");
        company1.addEmployee(user1);
        company1.setBalance(5000f);
        Company company2 = new Company(2, "Apple");
        company2.addEmployee(user2);
        company2.setBalance(8000f);
        companyService.addCompany(company1);
        companyService.addCompany(company2);


    }
}
