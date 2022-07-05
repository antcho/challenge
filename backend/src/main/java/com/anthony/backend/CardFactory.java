package com.anthony.backend;

import com.anthony.backend.model.Card;
import com.anthony.backend.model.GiftCard;
import com.anthony.backend.model.MealCard;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Component
public class CardFactory {

    public GiftCard createGiftCard(Float initialValue) {
        return new GiftCard(UUID.randomUUID(), initialValue, initialValue, LocalDate.now());
    }

    public MealCard createMealCard(Float initialValue) {
        return new MealCard(UUID.randomUUID(), initialValue, initialValue, LocalDate.now());
    }
}
