package com.fang.algorithm;

/*
 *   Daily Coding Problem
 *   [ContextLogic]
 *
 *   Implement division of two positive integers without using the division, multiplication, or modulus operators.
 *   Return the quotient as an integer, ignoring the remainder.
 */
public class Division {
    /**
     * Divide integer a by integer b. This implementation also includes negative
     * integers
     * 
     * @param a
     * @param b
     * @return int
     */
    static int divide(int a, int b) {
        if (b == 0)
            throw new IllegalArgumentException("cannot divide by 0!");

        int count = 0;
        while (a != 0) {
            if (Math.abs(a) < Math.abs(b))
                return count;
            if ((a ^ b) < 0) { // opposite sign
                a += b;
                count--;
            } else {
                a -= b;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(divide(11, 4)); // 2
        System.out.println(divide(18, 2)); // 9
        System.out.println(divide(6, 3)); // 2
        System.out.println(divide(4, 4)); // 1
        System.out.println(divide(16, -4)); // -4
        System.out.println(divide(-16, 4)); // -4
        System.out.println(divide(4, 8)); // 0
        System.out.println(divide(4, -8)); // 0
        System.out.println(divide(9, 0)); // exception
    }
}
