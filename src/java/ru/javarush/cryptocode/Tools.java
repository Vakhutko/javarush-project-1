package ru.javarush.cryptocode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Tools {
    public static Path getPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to the file: ");
        Path path = Path.of(scanner.nextLine());
        if (Files.isRegularFile(path)) {
            return path;
        } else {
            System.out.println("Wrong file! Try again.");
            return getPath();
        }
    }

    public static int getCode(int type) {
        Scanner scanner = new Scanner(System.in);
        if (type == 1) {
            System.out.print("Enter a crypto code(integer number): ");
        } else if (type == 2) {
            System.out.print("Enter a number of files to analysis style(from 0 to 5): ");
        }
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a number. Try again.");
            return getCode(type);
        }
    }
}