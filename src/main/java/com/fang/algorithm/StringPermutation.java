package com.fang.algorithm;

import java.util.ArrayList;
import java.util.List;


public class StringPermutation {

    static List<String> permutes(String str) {
        if (str == null) return null;
        
        List<String> allPerms = new ArrayList<>();
        if (str.length() == 0) { // base case
            allPerms.add("");

            return allPerms;
        }

        char c = str.charAt(0);
        String remain = str.substring(1);
        List<String> partialPerms = permutes(remain);

        for (String s : partialPerms) {
            for (int i = 0; i <= s.length(); i++) { // got a bug <=, not <
                String newPerm = insertAt(s, c, i);
                allPerms.add(newPerm);
            }
        }
        return allPerms;
    }

    static String insertAt(String s, char c, int index) {
        String before = s.substring(0, index);
        String after = s.substring(index);
        return before + c + after;
    }

    public static void main(String[] args) {
        System.out.println(permutes("abc"));
        // [abc, bac, bca, acb, cab, cba]
    }
}
