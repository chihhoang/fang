package com.fang.algorithm;

public class PrintBinary {
    /**
     * Print all possible binary representations with [digits] number of digits
     * @param digits
     * @param prefix
     */
    static void printBinary(int digits, String prefix) {
        if (digits == 0) {
            System.out.println(prefix);
        }
        else {
            printBinary(digits - 1, prefix + "0");
            printBinary(digits - 1, prefix + "1");
        }
    }

    public static void main(String[] args) {
        printBinary(3, "");
        // printBinary(5, "");
        // printBinary(7, "");
    }
}