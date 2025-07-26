package hr.code.java;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
The problem requires finding the largest connected region of 1s in a 2D grid, where cells are connected horizontally, vertically, or diagonally.
4
4
1 1 0 0
0 1 1 0
0 0 1 0
1 0 0 0
*/


public class ConnectedCellInAGrid {

    private static boolean[][] visited;
    private static int[][] grid;

    public static void main(String[] args) {
        List<List<Integer>> matrix = Arrays.asList(Arrays.asList(1, 1, 0, 0), Arrays.asList(0, 1, 1, 0), Arrays.asList(0, 0, 1, 0), Arrays.asList(1, 0, 0, 0));
        System.out.println(connectedCell(matrix));

    }


    public static int connectedCell(List<List<Integer>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        visited = new boolean[rows][cols];
        grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = matrix.get(i).get(j);
            }
        }
        int size = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    size = Math.max(size, iterativeDFS(i, j));
                }
            }
        }

        return size;

    }

    public static int iterativeDFS(int r, int c) {
        int[] rowDir = {-1, -1, -1, 0, 0, 1, 1, 1}; // 8 directions (diagonal, horizontal, vertical)
        int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};
        Stack<int[]> stack = new Stack();
        stack.push(new int[]{r, c});
        visited[r][c] = true;
        int size = 0;
        while (!stack.empty()) {
            int[] cell = stack.pop();
            int x = cell[0], y = cell[1];
            size++;

            // Check all 8 possible directions
            for (int d = 0; d < 8; d++) {
                int newX = x + rowDir[d];
                int newY = y + colDir[d];

                if (isValid(grid, visited, newX, newY)) {
                    stack.push(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }

        }
        return size;
    }

    private static boolean isValid(int[][] grid, boolean[][] visited, int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1 && !visited[x][y];
    }
}
