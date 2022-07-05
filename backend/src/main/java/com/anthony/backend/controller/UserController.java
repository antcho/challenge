package com.anthony.backend.controller;

import com.anthony.backend.model.GiftCard;
import com.anthony.backend.model.MealCard;
import com.anthony.backend.model.User;
import com.anthony.backend.service.DepositService;
import com.anthony.backend.service.UserService;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class UserController {

    private final UserService userService;
    private final DepositService depositService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/expirationDates")
    public ResponseEntity<String> getExpirationDates(@PathVariable int userId) {
        User user = userService.getUserById(userId);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Gift Cards : ");
        for (GiftCard gc : user.getGiftCards()) {
            stringBuilder.append(gc.getId() + " : expires in " + depositService.getExpirationDate(gc) + " ");
        }

        stringBuilder.append(" \n Meal Cards : ");
        for (MealCard mc : user.getMealCards()) {
            stringBuilder.append(mc.getId() + " : expires in " + depositService.getExpirationDate(mc) + " ");
        }

        return ResponseEntity.status(HttpStatus.OK).body(stringBuilder.toString());
    }
}
