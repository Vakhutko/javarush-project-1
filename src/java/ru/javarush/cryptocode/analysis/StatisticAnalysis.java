package ru.javarush.cryptocode.analysis;

import ru.javarush.cryptocode.Data;
import ru.javarush.cryptocode.Tools;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StatisticAnalysis {

    public static void startStatisticAnalysis(Path path) {
        System.out.print("Choose type of analysis:\nW - By words\nL - By letter\nYour choose: ");
        switch (new Scanner(System.in).nextLine()) {
            case "W" -> analysisByWord(path);
            case "L" -> analysisByLetter(path);
            default -> {
                System.out.println("Wrong type! Try again.");
                startStatisticAnalysis(path);
            }
        }
    }

    private static void analysisByWord(Path path) {
        Set<Path> result = new HashSet<>();
        for (Path p : BruteForce.startBruteForce(path)) {
            if (p != null) {
                try {
                    for (String line : Files.readAllLines(p, StandardCharsets.UTF_8)) {
                        if (result.contains(p)) break;
                        String[] test = line.toLowerCase().split("\\b");
                        for (String word : Data.topWords) {
                            if (Arrays.asList(test).contains(word)) {
                                result.add(p);
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Broken file: " + p.getFileName());
                }
            }
        }
        for (Path p : result) {
            System.out.println(p.getFileName());
        }
    }

    private static void analysisByLetter(Path path) {
        Set<Character> topChars = fileAnalyze();
        for (Path p : BruteForce.startBruteForce(path)) {
            int countSameChars = 0;
            if (p != null) {
                Set<Character> currentFileTopChars = findTopLetters(p);
                for (char topChar : topChars) {
                    if (currentFileTopChars.contains(topChar)) {
                        countSameChars++;
                    }
                }
                if (topChars.size() - countSameChars < 3) {
                    System.out.println(p.getFileName());
                }
            }
        }
    }

    private static Set<Character> fileAnalyze() {
        Set<Character> setOfTopCharacters = new HashSet<>();
        int countFileToAnalysis = Tools.getCode(2);
        for (int i = 0; i < countFileToAnalysis; i++) {
            setOfTopCharacters.addAll(findTopLetters(Tools.getPath()));
        }
        return setOfTopCharacters;
    }

    private static Set<Character> findTopLetters(Path path) {
        Map<Character, BigInteger> charsCount = new TreeMap<>();
        Set<Character> setOfTopCharacters = new HashSet<>();
        for (char c : Data.alphabet) {
            charsCount.put(c, BigInteger.ZERO);
        }
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String s : lines) {
                for (int j = 0; j < s.length(); j++) {
                    if (charsCount.containsKey(s.charAt(j))) {
                        charsCount.put(s.charAt(j), charsCount.get(s.charAt(j)).add(BigInteger.ONE));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List sortedListOfTopCharacters = charsCount.entrySet().stream()
                .sorted(Map.Entry.<Character, BigInteger>comparingByValue().reversed()).toList();
        for (int i = 0; i < 5; i++) {
            setOfTopCharacters.add(((Map.Entry<Character, BigInteger>) sortedListOfTopCharacters.get(i)).getKey());
        }
        return setOfTopCharacters;
    }
}
