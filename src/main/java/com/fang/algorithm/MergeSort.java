package com.fang.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    static void mergeSort(List<Integer> v) {
        if (v.size() <= 1)
            return;

        // need to make copies here, otherwise we'd merge the original list which is
        // incorrect!
        List<Integer> left = new ArrayList<>(v.subList(0, v.size() / 2));
        List<Integer> right = new ArrayList<>(v.subList(v.size() / 2, v.size()));

        mergeSort(left);
        mergeSort(right);

        // merge
        int i1 = 0; // index left
        int i2 = 0; // index right
        for (int i = 0; i < v.size(); i++) {
            // Take from left if
            // - nothing remaining from right
            // - thing on left < right
            if (i2 >= right.size() || (i1 < left.size() && left.get(i1) < right.get(i2))) {
                v.set(i, left.get(i1));
                i1++;
            } else {
                v.set(i, right.get(i2));
                i2++;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 7, 3, 4, 1, 9, 2);
        System.out.println(list); // [5, 7, 3, 4, 1, 9, 2]
        mergeSort(list);
        System.out.println(list); // [1, 2, 3, 4, 5, 7, 9]
    }
}
