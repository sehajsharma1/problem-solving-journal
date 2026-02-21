package com.ocean.problemsolvingjournal.leetcode;

public class UniquePathsIIM {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if ((i - 1) < 0 && (j - 1) >= 0 && obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i][j-1];
                } else if ((i - 1) >= 0 && (j - 1) < 0 && obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j];
                } else if ((i - 1) >= 0 && obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = 0;
                }

            }

        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];

    }
    public int uniquePathsWithObstaclesV1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp array initialized with -1
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return dfs(0, 0, grid, dp);
    }

    private int dfs(int i, int j, int[][] grid, int[][] dp) {
        int m = grid.length;
        int n = grid[0].length;

        // out of boundary
        if (i >= m || j >= n) {
            return 0;
        }

        // obstacle
        if (grid[i][j] == 1) {
            return 0;
        }

        // reached destination
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        // already computed
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int down = dfs(i + 1, j, grid, dp);
        int right = dfs(i, j + 1, grid, dp);

        dp[i][j] = down + right;
        return dp[i][j];
    }

    public static void main(String[] args) {
        UniquePathsIIM solver = new UniquePathsIIM();

        int[][] grid1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}; // expected 2
        System.out.println("grid1 -> expected 2, got: " + solver.uniquePathsWithObstaclesV1(grid1));

        int[][] grid2 = {{0, 1}, {0, 0}}; // expected 1
        System.out.println("grid2 -> expected 1, got: " + solver.uniquePathsWithObstacles(grid2));

        int[][] grid3 = {{1}}; // expected 0 (start blocked)
        System.out.println("grid3 -> expected 0, got: " + solver.uniquePathsWithObstacles(grid3));

        int[][] grid4 = {{0}}; // expected 1
        System.out.println("grid4 -> expected 1, got: " + solver.uniquePathsWithObstacles(grid4));
    }

}
