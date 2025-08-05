package com.heitor.Bank.BankAccount.Utils;

import java.util.Random;

public class BankAccountDataGenerator {

    private static final Random random = new Random();

    public static int generateAccountNumber() {
        return 100000 + random.nextInt(900000);
    }

    public static int generateAgencyNumber() {
        return 1000+ random.nextInt(9000);
    }
}
