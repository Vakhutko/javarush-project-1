package ru.javarush.cryptocode;

import ru.javarush.cryptocode.analysis.BruteForce;
import ru.javarush.cryptocode.analysis.StatisticAnalysis;
import ru.javarush.cryptocode.encryption.Encryption;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Description:");
        for (Types t : Types.values()) {
            System.out.println(t + t.getDescription());
        }
        while (true) {
            System.out.print("Enter EXIT to close or enter a type(ENCRYPTION\\DECRYPTION\\CRYPTO_BRUTE_FORCE\\CRYPTO_STATISTIC_ANALYSIS): ");
            String result = scanner.nextLine();
            if (result.equals("EXIT")) break;
            try {
                Types type = Types.valueOf(result);
                Path path = Tools.getPath();
                switch (type) {
                    case ENCRYPTION -> Encryption.startEncryption(path, Tools.getCode(1), 1);
                    case DECRYPTION -> Encryption.startEncryption(path, Tools.getCode(1), 2);
                    case CRYPTO_BRUTE_FORCE -> BruteForce.startBruteForce(path);
                    case CRYPTO_STATISTIC_ANALYSIS -> StatisticAnalysis.startStatisticAnalysis(path);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong type. Try again.");
            }
        }
    }
}
