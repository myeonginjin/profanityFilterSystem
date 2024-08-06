package com.study.profanityFilterSystem.utils;

import java.io.*;
import java.util.*;

public class BanWordLoader {

    public static List<String> loadBanWords(String fileName) throws IOException {
        List<String> banWords = new ArrayList<>();
        try (InputStream inputStream = BanWordLoader.class.getClassLoader().getResourceAsStream("banWords.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                banWords.add(line.trim());
            }
        }
        return banWords;
    }
}