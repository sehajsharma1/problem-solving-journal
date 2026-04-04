package com.ocean.problemsolvingjournal.leetcode;

/*
Given an m x n integer  matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
*/
public class SetMatrixZeroesM {

    public void setZeroes(int[][] matrix) {
        boolean zeroInFirstRow = false;
        boolean zeroInFirstColumn = false;
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;

        for (int column = 0; column < columnLength; column++) {
            if (matrix[0][column] == 0) {
                zeroInFirstRow = true;
                break;
            }
        }

        for (int row = 0; row < rowLength; row++) {
            if (matrix[row][0] == 0) {
                zeroInFirstColumn = true;
                break;
            }
        }

        for (int row = 1; row < rowLength; row++) {
            for (int col = 1; col < columnLength; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for (int m = 1; m < columnLength; m++) {
            if (matrix[0][m] == 0) {
                int n = 0;
                while (n < rowLength) {
                    matrix[n][m] = 0;
                    n++;
                }
            }
        }

        for (int m = 1; m < rowLength; m++) {
            if (matrix[m][0] == 0) {
                int n = 0;
                while (n < columnLength) {
                    matrix[m][n] = 0;
                    n++;
                }
            }
        }

        if (zeroInFirstRow) {
            for (int k = 0; k < columnLength; k++) {
                matrix[0][k] = 0;
            }
        }

        if (zeroInFirstColumn) {
            for (int k = 0; k < rowLength; k++) {
                matrix[k][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        SetMatrixZeroesM obj = new SetMatrixZeroesM();
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        obj.setZeroes(matrix);
    }
}
