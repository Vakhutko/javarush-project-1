package ru.javarush.cryptocode.encryption;

import ru.javarush.cryptocode.Data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Encryption {

    public static Path startEncryption(Path path, int code, int type) {
        Path result = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                for (int i = 0; i < line.length(); i++) {
                    for (int j = 0; j < Data.alphabet.length; j++) {
                        if (line.charAt(i) == Data.alphabet[j]) {
                            if (type == 1) {
                                int index = j + code < 0 ? Data.alphabet.length + ((j + code) % Data.alphabet.length) : j + code;
                                stringBuilder.append(Data.alphabet[index % Data.alphabet.length]);
                            } else if (type == 2) {
                                int index = j - code < 0 ? Data.alphabet.length + ((j - code) % Data.alphabet.length) : j - code;
                                stringBuilder.append(Data.alphabet[index % Data.alphabet.length]);
                            }
                        }
                    }
                }
                stringBuilder.append('\n');
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
            String[] splitFilePath = path.toString().split("\\.", path.toString().lastIndexOf('.'));
            if (type == 1) {
                result = Path.of(splitFilePath[0] + "_resolve." + splitFilePath[1]);
            } else if (type == 2) {
                result = Path.of(splitFilePath[0] + "_decode" + code + "." + splitFilePath[1]);
            }
            assert result != null;
            Files.write(result, stringBuilder.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Broken file: " + path.getFileName());
        }
        return result;
    }
}
