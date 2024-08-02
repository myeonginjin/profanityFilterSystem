package com.study.profanityFilterSystem.filtering.AhoCorasick;

import java.util.*;
import java.util.regex.*;

public class BadWordFiltering {
    private final Set<String> badWordsSet = new HashSet<>();
    private final AhoCorasick ahoCorasick = new AhoCorasick();

    public void addBadWords(Collection<String> badWords) {
        badWordsSet.addAll(badWords);
        for (String word : badWords) {
            ahoCorasick.addKeyword(word);
        }
        ahoCorasick.buildFailureLinks();
    }

    // 문자열에서 특정 문자 제거
    private String preprocessInput(String input) {
        String patternText = "[\\s,.!?@1]*";
        return input.replaceAll(patternText, "");
    }

    public boolean checkBadWord(String input) {
        String preprocessedInput = preprocessInput(input);
        Set<String> foundBadWords = ahoCorasick.search(preprocessedInput);
        return !foundBadWords.isEmpty();
    }
}
