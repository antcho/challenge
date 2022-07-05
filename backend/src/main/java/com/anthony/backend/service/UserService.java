package com.anthony.backend.service;

import com.anthony.backend.model.Card;
import com.anthony.backend.model.GiftCard;
import com.anthony.backend.model.MealCard;
import com.anthony.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public User getUserById(int userId) {
        return users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst().get();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void giveCardToUser(Card card, User user) {
        if (card instanceof GiftCard) {
            user.getGiftCards().add((GiftCard) card);
            user.setGiftBalance(user.getGiftBalance() + card.getInitialValue());
        } else if (card instanceof MealCard) {
            user.getMealCards().add((MealCard) card);
            user.setMealBalance(user.getMealBalance() + card.getInitialValue());
        }
    }
}
