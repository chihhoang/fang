package com.fang.projecteuler;

/*
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.
*/
public class MultiplesOf3And5 {
    /**
     * Brute force?
     */
    static int getSumOfMutiples3And5(int n) {
        int sum = 0;
        for (int i = 3; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0)
                sum += i;
        }

        return sum;
    }

    /**
     * Optimized
     * 
     * @param n    divisor
     * @param upTo multiples up to this number
     * @return sum of multiples of n with until upTo
     */
    static int sumDivisibleBy(int n, int upTo) {
        int sum = 0;
        for (int i = n; i < upTo; i += n) {
            sum += i;
        }

        return sum;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println(getSumOfMutiples3And5(1000)); // 233168
        System.out.println(System.nanoTime() - startTime); // 150771

        startTime = System.nanoTime();
        System.out.println(sumDivisibleBy(3, 1000) + sumDivisibleBy(5, 1000) - sumDivisibleBy(15, 1000)); // 233168
        System.out.println(System.nanoTime() - startTime); // 20586 appoximately 7 times faster
    }
}
