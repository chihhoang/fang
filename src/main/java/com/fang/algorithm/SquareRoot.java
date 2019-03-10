package com.fang.algorithm;

/*
[DCP]
Given a real number n, find the square root of n. For example, given n = 9, return 3.
*/

public class SquareRoot {
    /**
     * Use binary search to find the square root
     * @param n
     * @param error
     * @return the square root of n
     */
    static double sqrt(int n, double error) {
        if (n < 0)
            throw new IllegalArgumentException("Cannot take square root of a negative number " + n);
        double low = 0.0;
        double high = n;
        double guess = (low + high) / 2.0;
        
        while (Math.abs(guess * guess - n) >= error) {
            if (guess * guess > n) {
                high = guess;
            } else {
                low = guess;
            }

            guess = (low + high) / 2;
        }
        
        return guess;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(0, 0.1)); // 0.0
        System.out.println(sqrt(1, 0.0001)); // 0.999969482421875
        System.out.println(sqrt(2, 0.001)); // 1.4140625
        System.out.println(sqrt(9, 0.001)); // 3.000091552734375
        System.out.println(sqrt(25, 0.01)); // 5.00030517578125
        System.out.println(sqrt(30, 0.001)); // 5.477142333984375
        System.out.println(sqrt(49, 0.01)); // 6.999786376953125
        System.out.println(sqrt(50, 0.01)); // 7.0709228515625
    }
}
