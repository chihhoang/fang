package com.fang.algorithm;

import java.util.Arrays;

/*
[LeetCode]
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
You may assume the integer does not contain any leading zero, except the number 0 itself.

Input: [1,2,4]
Output: [1,2,5]
*/
public class PlusOne {
    static int[] plusOne(int[] digits) {
        if (digits == null)
            return null;

        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            int temp = digits[i] + carry;
            digits[i] = temp % 10;

            if (temp < 10)
                return digits;

            carry = temp >= 10 ? 1 : 0;
        }

        if (carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        return digits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[] { 1, 2, 3 })));
        System.out.println(Arrays.toString(plusOne(new int[] { 1, 2, 9 })));
        System.out.println(Arrays.toString(plusOne(new int[] { 1, 9, 9 })));
        System.out.println(Arrays.toString(plusOne(new int[] { 9, 8, 9 })));
        System.out.println(Arrays.toString(plusOne(new int[] { 9, 9, 9 })));
    }
}
