package com.anthony.backend.service;

import com.anthony.backend.BusinessException;
import com.anthony.backend.CardFactory;
import com.anthony.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;

@Service
public class DepositService {

    private final CardFactory cardFactory;
    private final UserService userService;

    @Autowired
    public DepositService(CardFactory cardFactory, UserService userService) {
        this.cardFactory = cardFactory;
        this.userService = userService;
    }



    public LocalDate getExpirationDate(Card card) {
        if (card instanceof MealCard) {
            Year expirationYear = Year.from(card.getDistributionDate().plusYears(1));
            int expirationDay = expirationYear.isLeap() ? 29 : 28;
            return LocalDate.of(expirationYear.getValue(), 2, expirationDay);
        }
        return card.getDistributionDate().plusDays(364); // /!\ not null
    }

   public boolean isExpired(Card card) {
        int comparedInt = LocalDate.now().compareTo(getExpirationDate(card));
        return comparedInt > 0;
   }

   public void distributeGift(Company company, User user, Float value) throws BusinessException {
        Card card = cardFactory.createGiftCard(value);
        distribute(company, user, card);
   }

   public void distributeMeal(Company company, User user, Float value) throws BusinessException {
        Card card = cardFactory.createMealCard(value);
        distribute(company, user, card);
   }

   private void distribute(Company company, User user, Card card) throws BusinessException {
        if (company.getBalance() < card.getInitialValue()) {
            throw new BusinessException("The " + company.getName() + " balance is too low to distribute a card.");
        }

        company.setBalance(company.getBalance() - card.getInitialValue());

        userService.giveCardToUser(card, user);
   }

}
