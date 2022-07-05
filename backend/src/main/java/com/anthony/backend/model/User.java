package com.anthony.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private final int id;
    private final String name;
    private List<GiftCard> giftCards;
    private Float giftBalance;
    private List<MealCard> mealCards;
    private Float mealBalance;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.giftCards = new ArrayList<>();
        this.giftBalance = 0f;
        this.mealCards = new ArrayList<>();
        this.mealBalance = 0f;
    }
}
