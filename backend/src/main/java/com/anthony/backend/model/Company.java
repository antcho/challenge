package com.anthony.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Company {
    private final int id;
    private final String name;
    private List<User> employees;
    private Float balance;

    public Company(int id, String name) {
        this.id = id;
        this.name = name;

        employees = new ArrayList<>();
        balance = 0f;
    }

    public void addEmployee(User user) {
        employees.add(user);
    }
}
