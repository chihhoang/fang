package com.fang.algorithm;

/*
Given a 2D boolean array, find the largest square subarray of
true values. The return value should be the side length of the
largest square subarray subarray.
*/
public class SquareSubmatrix {
    /**
     * Brute force. From each cell, find all biggest square submatrix for which 
     * it is the left upper corner
     * Time: O(n * m * 3^(n + m)) where n and m are matrix side sizes
     * Space: O(n + m) for recursive stack
     */
    static int squareSubmatrix(boolean[][] arr) {
        int max = 0;
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                max = Math.max(max, squareSubmatrix(arr, r, c));
            }
        }

        return max;
    }

    /**
     * Check the right, bottom, and adjacent cells
     * @return the biggest square which cell[r][c] is the upper left corner
     */
    static int squareSubmatrix(boolean[][] arr, int r, int c) {
        if (r == arr.length || c == arr[0].length) // hit bottom or most-right
            return 0;
        else if (!arr[r][c]) // false cell
            return 0;
        else { // current cell is true, find the size of its right, bottom, and adjacent cells and add 1 (current)
            return 1 + Math.min(squareSubmatrix(arr, r, c + 1),
            Math.min(squareSubmatrix(arr, r + 1, c),
            squareSubmatrix(arr, r + 1, c + 1)));
        }
    }

    /**
     * Top-down optimization: Cache the values to avoid repeated computations.
     */
    static int squareSubmatrix2(boolean[][] arr) {
        int[][] memo = new int[arr.length][arr[0].length];

        int max = 0;
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                max = Math.max(max, squareSubmatrix2(arr, r, c, memo));
            }
        }

        return max;
    }

    static int squareSubmatrix2(boolean[][] arr, int r, int c, int[][] memo) {
        if (r == arr.length || c == arr[0].length) // hit bottom or right-most
            return 0;
        else if (!arr[r][c]) // false cell
            return 0;
        else if (memo[r][c] > 0) // cached
            return memo[r][c];
        else {
            memo[r][c] = 1 + Math.min(squareSubmatrix(arr, r, c + 1),
            Math.min(squareSubmatrix(arr, r + 1, c),
            squareSubmatrix(arr, r + 1, c + 1)));
        }

        return memo[r][c];
    }

    /**
     * Bottom-up optimization: Work backward from the bottom right corner
     * Now r, c is the bottom right corner, check upper, left, and adjacent
     */
    static int squareSubmatrix3(boolean[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        int max = 0;

        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                if (r == 0 || c == 0)
                    dp[r][c] = arr[r][c] ? 1 : 0;
                else if (arr[r][c]) {
                    dp[r][c] = 1 + Math.min(dp[r - 1][c],
                    Math.min(dp[r][c - 1], dp[r - 1][c - 1]));
                }

                max = Math.max(max, dp[r][c]);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        boolean[][] arr = new boolean[][] {
            {true, true, true, true, false},
            {true, true, true, true, false},
            {true, true, true, true, false},
            {true, true, true, true, false},
            {true, true, true, true, false},
            {true, false, false, false, false},
        };

        long start = System.nanoTime();
        System.out.println(squareSubmatrix(arr)); // 4
        System.out.println(System.nanoTime() - start); // 751132
        start = System.nanoTime();
        System.out.println(squareSubmatrix2(arr));
        System.out.println(System.nanoTime() - start); // 103436
        start = System.nanoTime();
        System.out.println(squareSubmatrix3(arr));
        System.out.println(System.nanoTime() - start); // 35283
    }
}
