package com.fang.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Permutations
[Leetcode]

Given a collection of distinct integers, return all possible permutations.

Input: [1,2,3]
Output:
[
  [1,2,3], [1,3,2], [2,1,3],
  [2,3,1], [3,1,2], [3,2,1]
]
*/
public class PermutationI {
    public List<List<Integer>> permute(int[] nums) {
        return permute(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    List<List<Integer>> permute(List<Integer> nums) {
        if (nums == null) return null;
        
        List<List<Integer>> allPerms = new ArrayList<>();
        if (nums.size() == 0) { // base case, add empty list
            allPerms.add(new ArrayList<>());
            return allPerms;
        }

        int first = nums.get(0);
        List<Integer> remain = nums.subList(1, nums.size());
        List<List<Integer>> perms = permute(remain);

        for (List<Integer> perm : perms) {
            for (int i = 0; i <= perm.size(); i++) {
                List<Integer> newList = insertAt(perm, first, i);
                allPerms.add(newList);
            }
        }

        return allPerms;
    }

    List<Integer> insertAt(List<Integer> list, int value, int index) {
        List<Integer> result = new ArrayList<>(list);
        result.add(index, value);
        
        return result;
    }

    public static void main(String[] args) {
        PermutationI solution = new PermutationI();

        List<List<Integer>> list = solution.permute(new int[] {1,2,3});
        System.out.println(list);
        // [[1, 2, 3], [2, 1, 3], [2, 3, 1], [1, 3, 2], [3, 1, 2], [3, 2, 1]]
    }
}
