package com.fang.algorithm;

/**
 * [Apple] phone screen
 * 
 * Print out the grade-school multiplication table up to 12x12 
 * Multiplication output
 * 
 * 1  2  3  4   5   6   7   8   9  10  11  12
 * 
 * 2  4  6  8  10  12  14  16  18  20  22  24
 * 
 * ...
 */
public class MultiplicationTable {

    static void printMultiplicationTable() {
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= 12; j++) {
                System.out.printf("%3d ", i * j);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printMultiplicationTable();
    }
}