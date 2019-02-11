package com.fang.projecteuler;

/*
Euler #3: Largest prime factor
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?
*/
public class LargestPrimeFactor {
    static long largestPrimeFactor(long n) {
        if (n < 2)
            throw new IllegalArgumentException("Input cannot be less than 2.");

        while (n % 2 == 0)
            n /= 2;

        if (n == 1)
            return 2;

        long i = 3;
        // eliminate prime factors till sqrt(n)
        for (; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0)
                n /= i;
        }

        if (n > 2)
            return n;
        else {
            return i -= 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(largestPrimeFactor(600851475143L)); // 6857
    }
}
