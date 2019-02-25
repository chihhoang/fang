package com.fang.algorithm;

public class EightQueens {
    static int count = 0;

    static void solveQueens(int[][] board) {
        queensHelper(board, 0);
    }

    static void queensHelper(int[][] board, int col) {
        if (col > 7) {
            printBoard(board);
            count++;
        } else {
            for (int row = 0; row < 8; row++) {
                if (isSafe(board, row, col)) {
                    board[row][col] = 1; // chose
                    queensHelper(board, col + 1); // explore
                    board[row][col] = 0; // unchoose/backtrack
                }
            }
        }
    }

    static boolean isSafe(int[][] board, int row, int col) {
        int i, j;
        for (i = 0; i < board.length; i++) {
            if (i == row)
                continue;
            // make sure row has no Q, denote 1, probably just need to check from the left
            if (board[i][col] == 1)
                return false;
        }
        for (j = 0; j < board[0].length; j++) { // needed?
            if (j == col)
                continue;
            // make sure col has no Q
            if (board[row][j] == 1)
                return false;
        }
        // check upper left diagonals
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }
        // check lower left diagonals
        for (i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    static void printBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] board = new int[8][8];

        solveQueens(board);
        System.out.println("number of solutions = " + count); // 92
    }
}
