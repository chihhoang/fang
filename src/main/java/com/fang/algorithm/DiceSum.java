package com.fang.algorithm;

import java.util.ArrayList;
import java.util.List;

public class DiceSum {
    static void diceSum(int n, int targetSum) {
        diceHelper(n, targetSum, 0, new ArrayList<>());
    }

    static int count;
    static void diceHelper(int n, int targetSum, int sumSoFar, List<Integer> chosen) {
        count++;
        if (n == 0) {
            // if (targetSum == sumSoFar)
                System.out.println(chosen);
        } else {
            for (int i = 1; i <= 6; i++) {
                // limit our choices to the targetSum
                // too big, pick smaller i
                // too small, pick bigger i
                if (sumSoFar + i + 1*(n - 1) <= targetSum
                && sumSoFar + i + 6*(n - 1) >= targetSum) {
                    chosen.add(i);
                    diceHelper(n - 1, targetSum, sumSoFar + i, chosen);
                    chosen.remove(chosen.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        diceSum(3, 7);
        // System.out.println("calls: " + count); // 133 with (sumSoFar < targetSum)
        System.out.println("calls: " + count); // 36
    }
}
