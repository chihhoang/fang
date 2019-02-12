package com.fang.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
[DCP]
[Square]

Given a string and a set of characters, return the shortest substring containing all the characters in the set.

For example, given the string "figehaeci" and the set of characters {a, e, i}, you should return "aeci".

If there is no substring containing all the characters in the set, return null.
*/
public class SubstringFromSet {
    static String shortestSubstringFromSet(String str, Set<Character> charSet) {
        List<String> allSubstringSet = new ArrayList<>();
        int startIndex = 0;
        int shortestLength = Integer.MAX_VALUE;
        
        for (int i = 0; i < str.length() - charSet.size(); i++) {
            if (charSet.contains(str.charAt(i))) {
                startIndex = i;
                break;
            }
        }

        // going to the next char now, but should go to the next char that in the set
        for (int j = startIndex; j < str.length() - charSet.size(); j++) {
            Set<Character> temp = new HashSet<>(charSet);
            for (int k = j; k < str.length(); k++) {
                if (temp.contains(str.charAt(k)))
                    temp.remove(str.charAt(k));
                if (temp.size() == 0) {
                    int newShortestLength = k - j + 1;
                    if (newShortestLength < shortestLength) {
                        shortestLength = newShortestLength;
                        allSubstringSet.add(str.substring(j, k + 1));
                    }
                }
            }
        }
        return allSubstringSet.size() != 0 ? allSubstringSet.get(allSubstringSet.size() - 1) : null;
    }

    public static void main(String[] args) {
        Set<Character> charSet = Stream.of('a', 'e', 'i').collect(Collectors.toSet());
        
        System.out.println(shortestSubstringFromSet("figehaeci", charSet)); // aeci
        
        System.out.println(shortestSubstringFromSet("gehaec", charSet)); // null
        
        charSet = Stream.of('o', 'r', 'k').collect(Collectors.toSet());
        System.out.println(shortestSubstringFromSet("geeksforgeeks", charSet)); // ksfor
    }
}
