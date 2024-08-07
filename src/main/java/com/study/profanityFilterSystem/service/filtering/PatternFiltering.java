package com.study.profanityFilterSystem.service.filtering;

import java.util.*;

public class PatternFiltering {
//    private final Set<String> banWordsSet = new HashSet<>();
//    private final Set<String> allowWordsSet = new HashSet<>();
    private final AhoCorasick ahoCorasick = new AhoCorasick();
    private List<int[]> outputWordPositionData;
    private List<String> outputWords;

    public void addWords(String wordType, Collection<String> words) {
        /*
        if(wordType.equals("banWord")) {
            banWordsSet.addAll(banWords);
        } else {
            allowWordsSet.addAll(banWords);
        }
         */
        for (String word : words) {
            ahoCorasick.addKeyword(wordType, word);
        }
        ahoCorasick.buildFailureLinks();
    }

    // 문자열에서 특정 문자 제거
    private String preprocessInput(String input) {
        String patternText = "[\\s,.!?@1234567890]*";
        return input.replaceAll(patternText, "");
    }

    public boolean checkBanWord(String input) {
        outputWordPositionData = new ArrayList<>();
        outputWords = new ArrayList<>();

        String preprocessedInput = preprocessInput(input);
        ahoCorasick.search(preprocessedInput,outputWordPositionData, outputWords);

        //System.out.println(outputWords);

        return !filterAllowWord().isEmpty();
    }

    public HashSet<String> filterAllowWord() {
        HashSet<String> banWords = new HashSet<>();

        System.out.println(outputWords);
        banWords.addAll(outputWords);
        return banWords;
    }
}
