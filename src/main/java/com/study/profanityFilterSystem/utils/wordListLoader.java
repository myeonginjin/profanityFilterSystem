package com.study.profanityFilterSystem.utils;

import java.io.*;
import java.util.*;

public class wordListLoader {

    public static List<String> loadWords(String wordType) throws IOException {
        List<String> words = new ArrayList<>();
        try (InputStream inputStream = wordListLoader.class.getClassLoader().getResourceAsStream(wordType+"s.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }
        return words;
    }
}