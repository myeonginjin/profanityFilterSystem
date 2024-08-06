package com.study.profanityFilterSystem.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AllowWordLoader {

    public static  List<String> loadAllowWords(String fileName) throws IOException {
        List<String> allowWords = new ArrayList<>();
        try (InputStream inputStream = AllowWordLoader.class.getClassLoader().getResourceAsStream("allowWords.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allowWords.add(line.trim());
            }
        }
        return allowWords;
    }
}
