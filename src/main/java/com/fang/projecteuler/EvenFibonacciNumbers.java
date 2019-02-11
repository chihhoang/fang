package com.fang.projecteuler;

/*
Euler #2: Even Fibonacci numbers
Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:

1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
*/
public class EvenFibonacciNumbers {
    static long sumEvenFibonacci(long limit) {
        long sum = 0;

        long first = 1;
        long second = 2;
        long third = 3;
        while (third < limit) {
            if (second % 2 == 0)
                sum += second;

            third = first + second;
            first = second;
            second = third;
        }

        return sum;
    }

    /**
     * Obserse that every third fibonacci is even, we only increment the sum on
     * every third fibonacci number
     * 
     * @param limit
     * @return sum of the even-valued fibonacci terms.
     */
    static long sumEvenFibonaccis(long limit) {
        long sum = 0;

        long first = 1;
        long second = 1;
        long third = first + second;
        while (third < limit) {
            sum += third;

            first = second + third;
            second = third + first;
            third = first + second;
        }

        return sum;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(sumEvenFibonacci(4_000_000)); // 4613732
        System.out.println(System.nanoTime() - start); // 125115

        start = System.nanoTime();
        System.out.println(sumEvenFibonaccis(4_000_000)); // 4613732
        System.out.println(System.nanoTime() - start); // 14717 about 8.5 times faster
    }
}
