package com.anthony.backend.service;

import com.anthony.backend.BusinessException;
import com.anthony.backend.CardFactory;
import com.anthony.backend.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositServiceTest {

    private Card giftCard = new GiftCard(UUID.randomUUID(), 100f, 100f, LocalDate.of(2022, 7, 5));
    private Card giftCard2 = new GiftCard(UUID.randomUUID(), 100f, 100f, LocalDate.of(2020, 7, 5));
    private Card mealCard = new MealCard(UUID.randomUUID(), 50f, 50f, LocalDate.of(2022, 7, 5));
    private Card mealCard2 = new MealCard(UUID.randomUUID(), 50f, 50f, LocalDate.of(2023, 2, 2));

    @Mock
    private CardFactory cardFactory;

    @Mock
    private UserService userService;

    @InjectMocks
    private DepositService depositService;


    @Test
    void getExpirationDate_gift() {
        assertEquals(LocalDate.of(2023, 7, 4), depositService.getExpirationDate(giftCard));
    }

    @Test
    void getExpirationDate_meal() {
        assertEquals(LocalDate.of(2023, 2, 28), depositService.getExpirationDate(mealCard));
    }

    @Test
    void getExpirationDate_meal_leapYear() {
        assertEquals(LocalDate.of(2024, 2, 29), depositService.getExpirationDate(mealCard2));
    }

    @Test
    void isExpired() {
        assertFalse(depositService.isExpired(giftCard));
        assertTrue(depositService.isExpired(giftCard2));
        assertFalse(depositService.isExpired(mealCard));
        assertFalse(depositService.isExpired(mealCard2));
    }


    @Test
    void distributeGift() throws BusinessException {
        Company company = new Company(1, "Tesla");
        company.setBalance(1000f);
        User user = new User(1, "Jojo");
        UUID uuid = UUID.randomUUID();
        GiftCard giftCard = new GiftCard(uuid, 100f, 100f, LocalDate.now());

        when(cardFactory.createGiftCard(100f)).thenReturn(giftCard);

        depositService.distributeGift(company, user, 100f);
        assertEquals(900f, company.getBalance());
        verify(userService).giveCardToUser(giftCard, user);
    }

    @Test
    void distributeGift_exception() {
        Company company = new Company(1, "Tesla");
        company.setBalance(10f);
        User user = new User(1, "Jojo");
        UUID uuid = UUID.randomUUID();

        when(cardFactory.createGiftCard(100f)).thenReturn(new GiftCard(uuid, 100f, 100f, LocalDate.now()));

        try {
            depositService.distributeGift(company, user, 100f);
            fail();
        } catch (BusinessException e) {
            assertEquals("The Tesla balance is too low to distribute a card.", e.getMessage());
        }
    }

    @Test
    void distributeMeal() throws BusinessException {
        Company company = new Company(1, "Apple");
        company.setBalance(1000f);
        User user = new User(1, "Dio");
        UUID uuid = UUID.randomUUID();
        MealCard mealCard = new MealCard(uuid, 100f, 100f, LocalDate.now());

        when(cardFactory.createMealCard(100f)).thenReturn(mealCard);

        depositService.distributeMeal(company, user, 100f);
        assertEquals(900f, company.getBalance());
        verify(userService).giveCardToUser(mealCard, user);
    }

    @Test
    void distributeMeal_exception() {
        Company company = new Company(1, "Apple");
        company.setBalance(10f);
        User user = new User(1, "Dio");
        UUID uuid = UUID.randomUUID();

        when(cardFactory.createMealCard(100f)).thenReturn(new MealCard(uuid, 100f, 100f, LocalDate.now()));

        try {
            depositService.distributeMeal(company, user, 100f);
            fail();
        } catch (BusinessException e) {
            assertEquals("The Apple balance is too low to distribute a card.", e.getMessage());
        }
    }
}