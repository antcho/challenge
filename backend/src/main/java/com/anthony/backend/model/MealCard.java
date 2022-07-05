package com.anthony.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MealCard implements Card {
    private final UUID id;
    private final Float initialValue;
    private Float currentValue;
    private final LocalDate distributionDate;
}
