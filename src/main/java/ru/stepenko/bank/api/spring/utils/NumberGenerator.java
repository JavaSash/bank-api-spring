package ru.stepenko.bank.api.spring.utils;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

/**
 * Утильный класс для генерации номера бакновского счёта и номера карты.
 */
@UtilityClass
public class NumberGenerator {

    private static final int START_INTERVAL = 1;
    private static final int endIntervalDefault = 9;
    private static int endInterval = endIntervalDefault;
    private static final int accountGenerateIterations = 20;
    private static final int cardGenerateIterations = 16;
    private static final Set<String> accounts = new HashSet<>();
    private static final Set<String> cards = new HashSet<>();

    /**
     * Генерирует случайный номер аккаунта
     * В случае коллизии перегенерирует номер, пока он не будет уникальным
     * @return 20-тизначный номер аккаунта
     */
    public static String generateAccountNumber() {
        String number = generateSequence(accountGenerateIterations);
        if (accounts.contains(number)) {
            generateAccountNumber();
        }
        accounts.add(number);
        return number;
    }

    /**
     * Генерирует случайный номер банковской карты
     * В случае коллизии перегенерирует номер, пока он не будет уникальным
     * @return 16-тизначный номер аккаунта
     */
    public static String generateCardNumber() {
        String number = generateSequence(cardGenerateIterations);
        if (cards.contains(number)) {
            generateCardNumber();
        }
        cards.add(number);
        return number;
    }

    private static int generateNumber() {
        endInterval = endIntervalDefault;
        endInterval -= START_INTERVAL;
        return (int) ((Math.random() * endInterval) + START_INTERVAL);
    }

    private static String generateSequence(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(generateNumber());
        }
        return new String(sb);
    }
}
