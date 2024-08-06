package com.study.profanityFilterSystem.service.filtering;

import java.util.*;

public class PatternFiltering {
    private final Set<String> banWordsSet = new HashSet<>();
    private final AhoCorasick ahoCorasick = new AhoCorasick();

    public void addBanWords(Collection<String> banWords, String wordType) {
        banWordsSet.addAll(banWords);
        for (String word : banWords) {
            ahoCorasick.addKeyword(word, wordType);
        }
        ahoCorasick.buildFailureLinks();
    }

    // 문자열에서 특정 문자 제거
    private String preprocessInput(String input) {
        String patternText = "[\\s,.!?@1234567890]*";
        return input.replaceAll(patternText, "");
    }

    public boolean checkBanWord(String input) {
        String preprocessedInput = preprocessInput(input);
        Set<String> foundBanWords = ahoCorasick.search(preprocessedInput);
        return !foundBanWords.isEmpty();
    }
}
