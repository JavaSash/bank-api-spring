package ru.stepenko.bank.api.spring.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGeneratorTest {

    @Test
    void generateAccountNumber() {
        assertEquals(20, NumberGenerator.generateAccountNumber().length());
    }

    @Test
    void generateCardNumber() {
        assertEquals(16, NumberGenerator.generateCardNumber().length());
    }
}