package com.fang.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
Word Count Engine
[Pramp]

Implement a document scanning function wordCountEngine, which receives a string document 
and returns a list of all unique words in it and their number of occurrences, 
sorted by the number of occurrences in a descending order. 
If two or more words have the same count, they should be sorted according to their order in the original sentence. 
Assume that all letters are in english alphabet. You function should be case-insensitive, so for instance, 
the words “Perfect” and “perfect” should be considered the same word.

The engine should strip out punctuation (even in the middle of a word) and use whitespaces to separate words.

Examples:
input:  document = "Practice makes perfect. you'll only
                    get Perfect by practice. just practice!"

output: [ ["practice", "3"], ["perfect", "2"],
          ["makes", "1"], ["youll", "1"], ["only", "1"], 
          ["get", "1"], ["by", "1"], ["just", "1"]
Note: Convert the frequencies of words to string in the final output
*/

public class WordCountEngine {
    static String[][] wordCountEngine(String document) {
        if (document == null || document.length() == 0)
            throw new IllegalArgumentException();

        String[] words = document.split("\\s+");
        Map<String, Integer> map = new LinkedHashMap<>(); // to keep the original order of words

        for (int i = 0; i < words.length; i++) {
            words[i] = processWord(words[i]);

            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        /*
         * Sort the map by values (frequencies of words)
         * Had to google up this part in the interview for clean code.
         * If I was to code this myself, the idea is to get
         * all the entries from the map and sort them by values, then add them to a
         * LinkedHashMap to reserve the original order of words
         */
        map = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        String[][] result = new String[map.size()][2];
        int i = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result[i++] = new String[] { entry.getKey(), String.valueOf(entry.getValue()) };
        }

        return result;
    }

    static String processWord(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // String document = "Practice makes perfect. you'll only get Perfect by
        // practice. just practice!";
        String document = "Every book is a quotation; and every house is a quotation out of all forests, and mines, and stone quarries; and every man is a quotation from all his ancestors. ";
        System.out.println(Arrays.deepToString(wordCountEngine(document)));
    }
}
