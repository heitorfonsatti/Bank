package com.heitor.Bank.Card.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardDataGenerator {

    public static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int block = (int)(Math.random() * 9000) + 1000;
            cardNumber.append(block);
        }
        return cardNumber.toString();
    }

    public static String generateCVV() {
        int cvv = (int)(Math.random() * 900) + 100;
        return String.valueOf(cvv);
    }

    public static String generateExpirationDate() {
        LocalDate now = LocalDate.now();
        int yearsToAdd = (int)(Math.random() * 5) + 1;
        LocalDate expirationDate = now.plusYears(yearsToAdd);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        return expirationDate.format(formatter);
    }
}
