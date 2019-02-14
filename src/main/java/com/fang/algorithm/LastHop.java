package com.fang.algorithm;
/*
[DCP]
[Pinterest]
Given an integer list where each number represents the number of hops you can make,
determine whether you can reach to the last index starting at index 0.

For example, [2, 0, 1, 0] returns true while [1, 1, 0, 1] returns false.
*/
public class LastHop {
    static boolean isLastHop(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;

        int curr = 0;
        while (curr < nums.length - 1) {
            curr += nums[curr];
            if (nums[curr] == 0 && curr != nums.length - 1)
                return false;
        }

        return curr == nums.length - 1;
    }

    public static void main(String[] args) {
        System.out.println(isLastHop(new int[] {2, 0, 1, 0})); // true
        System.out.println(isLastHop(new int[] {1, 1, 0, 1})); // false
        System.out.println(isLastHop(new int[] {0, 1, 0, 1})); // false
    }
}
