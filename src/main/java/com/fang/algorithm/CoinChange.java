package com.fang.algorithm;

/*
Given an integer representing a given amount of change, write a
function to compute the total number of coins required to make
that amount of change. You can assume that there is always a
1¢ coin.
*/
public class CoinChange {
    static int[] coins = new int[] { 1, 5, 10 };

    /**
     * Brute force: Recursively check and compare all possible combinations
     * 
     * @param n
     * @return minimum number of coins needed to make up n
     */
    static int coinChange(int n) {
        if (n == 0)
            return 0;

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= n) {
                minCoins = Math.min(minCoins, coinChange(n - coin));
            }
        }

        return minCoins + 1;
    }

    static int coinChange2(int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return coinChange2(n, memo);
    }

    /**
     * Top-down optimization: Save the minimum numbers of coins to make 1, 2,...
     * coins in cache
     */
    static int coinChange2(int n, int[] memo) {
        if (n < 0)
            return -1;
        if (n == 0)
            return 0;

        if (memo[n] > -1)
            return memo[n];

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= n) {
                minCoins = Math.min(minCoins, coinChange2(n - coin, memo));
            }
        }

        memo[n] = minCoins + 1;
        return memo[n];
    }

    /**
     * Bottom-up optimization: Start from n = 1,2,... and save the result in cache
     * till n
     */
    static int coinChange3(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minCoins = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin <= i) {
                    minCoins = Math.min(minCoins, dp[i - coin] + 1);
                }
            }

            dp[i] = minCoins;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(coinChange(50)); // 5
        System.out.println(System.nanoTime() - start); // 29647695
        start = System.nanoTime();
        System.out.println(coinChange2(50));
        System.out.println(System.nanoTime() - start); // 32392
        start = System.nanoTime();
        System.out.println(coinChange3(50));
        System.out.println(System.nanoTime() - start); // 19501
    }
}
