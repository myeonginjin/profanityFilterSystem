package com.study.profanityFilterSystem.service.filtering;

import java.util.*;

public class AhoCorasick {

    private TrieNode root;

    public AhoCorasick() {
        root = new TrieNode();
    }

    public void addKeyword(String wordType, String keyword) {
        TrieNode node = root;
        for (char c : keyword.toCharArray()) {
            System.out.println(c + "?");
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.outputs.put(wordType, keyword);
        System.out.println(node.outputs);
    }

    public void buildFailureLinks() {
        Queue<TrieNode> queue = new LinkedList<>();
        root.fail = root;

        for (TrieNode node : root.children.values()) {
            node.fail = root;
            queue.add(node);
        }

        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();

            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                char c = entry.getKey();
                TrieNode child = entry.getValue();
                queue.add(child);

                TrieNode failNode = current.fail;
                while (failNode != root && !failNode.children.containsKey(c)) {
                    failNode = failNode.fail;
                }
                if (failNode.children.containsKey(c)) {
                    child.fail = failNode.children.get(c);
                } else {
                    child.fail = root;
                }
                child.outputs.putAll(child.fail.outputs);
            }
        }
    }

    public Set<String> search(String text) {
        Set<String> result = new HashSet<>();
        TrieNode node = root;

        for (char c : text.toCharArray()) {
            while (node != root && !node.children.containsKey(c)) {
                node = node.fail;
            }
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                node = root;
            }

            //System.out.println(node.outputs.size() +" size");

            //일단 아웃풋 중에 금칙어만 추가 (로직 변경 필요)
            for (Map.Entry<String, String> entry : node.outputs.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                if (entry.getKey().equals("banWord")) {result.add(entry.getValue());}
            }
            //result.addAll(node.outputs.values());
        }
        return result;
    }

//    public String searchAllowWord(String text) {
//        TrieNode node = root;
//
//        for (char c : text.toCharArray()) {
//            while (node != root && !node.children.containsKey(c)) {
//                node = node.fail;
//            }
//            if (node.children.containsKey(c)) {
//                node = node.children.get(c);
//            } else {
//                node = root;
//            }
//            result.addAll(node.outputs);
//        }
//
//    }
}