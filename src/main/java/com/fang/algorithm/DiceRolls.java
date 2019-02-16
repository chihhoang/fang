package com.fang.algorithm;

import java.util.ArrayList;
import java.util.List;

public class DiceRolls {
    /**
     * Print all possible results of rolling n numbers of dices
     * @param n
     */
    static void diceRolls(int n) {
        List<Integer> chosen = new ArrayList<>();
        diceHelper(n, chosen);
    }

    static void diceHelper(int n, List<Integer> chosen) {
        System.out.println("diceHelper " + n + " " + chosen);
        if (n == 0)
            System.out.println(chosen);
        else {
            for (int i = 1; i <= 6; i++) {
                chosen.add(i);
                diceHelper(n - 1, chosen);
                chosen.remove(chosen.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        diceRolls(2);
    }
}
