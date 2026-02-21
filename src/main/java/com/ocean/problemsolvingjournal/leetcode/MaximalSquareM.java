package com.ocean.problemsolvingjournal.leetcode;

/*
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
*/
public class MaximalSquareM {

    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int num = matrix[i][j] - '0';
                if ((i - 1) >= 0 && (j - 1) >= 0 && num != 0) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            (Math.min(dp[i - 1][j], dp[i][j - 1])));
                } else {
                    dp[i][j] = num;
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }

    public static void main(String[] args) {
        // Sample matrix (from common maximal square examples)
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        MaximalSquareM solver = new MaximalSquareM();
        int maxSide = solver.maximalSquare(matrix);
        System.out.println("maximalSquare returned: " + maxSide);
    }
}
