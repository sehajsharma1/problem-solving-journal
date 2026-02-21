package com.ocean.problemsolvingjournal.leetcode;

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
*/
public class MinimumPathSumM {

    public static void main(String[] args) {
        MinimumPathSumM solver = new MinimumPathSumM();
        int[][] grid1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println("minPathSum(grid1) = " + solver.minPathSumV1(grid1));
        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println("minPathSum(grid2) = " + solver.minPathSum(grid2));

    }

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int[] arr = grid[i];
            for (int j = 0; j < arr.length; j++) {
                if ((i - 1) < 0 && (j - 1) >= 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if ((i - 1) >= 0 && (j - 1) < 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else if ((i - 1) >= 0) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public int minPathSumV1(int[][] grid) {
        int[][] dp = new int[grid.length + 1][grid[0].length + 1];
        return f(grid, 0, 0, dp);
    }

    public int f(int[][] grid, int i, int j, int[][] dp) {
        if (i >= grid.length || j >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        if (dp[i][j] != 0) return dp[i][j];
        int r = f(grid, i + 1, j, dp);
        int d = f(grid, i, j + 1, dp);
        return dp[i][j] = grid[i][j] + Math.min(r, d);
    }
}
