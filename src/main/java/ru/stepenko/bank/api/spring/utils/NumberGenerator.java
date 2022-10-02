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
    private static final int DEFAULT_END_INTERVAL = 9;
    private static int endInterval = DEFAULT_END_INTERVAL;
    private static final int ACCOUNT_GENERATE_ITERATIONS = 20;
    private static final int CARD_GENERATE_ITERATIONS = 16;
    private static final Set<String> ACCOUNTS = new HashSet<>();
    private static final Set<String> CARDS = new HashSet<>();

    /**
     * Генерирует случайный номер аккаунта
     * В случае коллизии перегенерирует номер, пока он не будет уникальным
     * @return 20-тизначный номер аккаунта
     */
    public static String generateAccountNumber() {
        String number = generateSequence(ACCOUNT_GENERATE_ITERATIONS);
        if (ACCOUNTS.contains(number)) {
            generateAccountNumber();
        }
        ACCOUNTS.add(number);
        return number;
    }

    /**
     * Генерирует случайный номер банковской карты
     * В случае коллизии перегенерирует номер, пока он не будет уникальным
     * @return 16-тизначный номер аккаунта
     */
    public static String generateCardNumber() {
        String number = generateSequence(CARD_GENERATE_ITERATIONS);
        if (CARDS.contains(number)) {
            generateCardNumber();
        }
        CARDS.add(number);
        return number;
    }

    private static int generateNumber() {
        endInterval = DEFAULT_END_INTERVAL;
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
