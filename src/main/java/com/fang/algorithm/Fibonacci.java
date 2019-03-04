package com.fang.algorithm;

public class Fibonacci {
    /**
     * Return the nth fibonacci number using raw recursion O(2^n)
     */
    static int fibo(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        if (n < 2)
            return n;

        return fibo(n - 1) + fibo(n - 2);
    }

    /**
     * Return the nth fibonacci number using top-down recursion (memoization) O(n)
     */
    static int fibo2(int n) {
        if (n < 0)
            throw new IllegalArgumentException();

        int memo[] = new int[n + 1];

        return memoHelper(n, memo);
    }

    static int memoHelper(int n, int[] memo) {
        if (n == 0 || n == 1)
            return n;

        if (memo[n] == 0) {
            memo[n] = memoHelper(n - 1, memo) + memoHelper(n - 2, memo);
        }

        return memo[n];
    }

    /**
     * Return the nth fibonacci number using bottom-up (dynamic programming) O(n)
     */
    static int fibo3(int n) {
        if (n < 0)
            throw new IllegalArgumentException();

        int memo[] = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(fibo(20)); // 6765
        System.out.println(System.nanoTime() - start); // 477675
        start = System.nanoTime();
        System.out.println(fibo2(20));
        System.out.println(System.nanoTime() - start); // 18591
        start = System.nanoTime();
        System.out.println(fibo3(20));
        System.out.println(System.nanoTime() - start); // 13844
    }
}