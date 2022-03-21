package ru.javarush.cryptocode.analysis;

import ru.javarush.cryptocode.Data;
import ru.javarush.cryptocode.encryption.Encryption;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BruteForce {

    public static List<Path> startBruteForce(Path path) {
        List<Path> files = new ArrayList<>();
        for (int i = 1; i < Data.alphabet.length; i++) {
            files.add(Encryption.startEncryption(path, i, 2));
        }
        return files;
    }
}