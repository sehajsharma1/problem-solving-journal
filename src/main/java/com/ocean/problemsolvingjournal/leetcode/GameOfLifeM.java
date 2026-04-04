package com.ocean.problemsolvingjournal.leetcode;

/*
According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state of the board is determined by applying the above rules simultaneously to every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.

Given the current state of the board, update the board to reflect its next state.

Note that you do not need to return anything.

Example 1:
Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

*/
public class GameOfLifeM {
    public void gameOfLife(int[][] board) {

        int[][] matrix = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, matrix[i], 0, board[i].length);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int number = board[i][j];
                if (number == 1) {
                    int neighbors = getNeighBors(i, j, matrix);
                    if (neighbors < 2) {
                        board[i][j] = 0;
                    } else if (neighbors > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    int neighbors = getNeighBors(i, j, matrix);
                    if (neighbors == 3) {
                        board[i][j] = 1;
                    }
                }
            }

        }


    }

    public void gameOfLifeV1(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int liveNeighbors = 0;

                for (int k = 0; k < 8; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if (ni >= 0 && ni < m && nj >= 0 && nj < n
                            && Math.abs(board[ni][nj]) == 1) {
                        liveNeighbors++;
                    }
                }

                // Apply rules
                if (board[i][j] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[i][j] = -1; // live -> dead
                    }
                } else {
                    if (liveNeighbors == 3) {
                        board[i][j] = 2; // dead -> live
                    }
                }
            }
        }

        // Step 2: Final update
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    private int getNeighBors(int row, int col, int[][] matrix) {
        int[] rowDir = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int newRow = row + rowDir[i];
            int newCol = col + colDir[i];
            if (newRow >= 0 && newCol >= 0 && newRow < matrix.length && newCol < matrix[0].length) {
                int number = matrix[newRow][newCol];
                if (number == 1) {
                    count++;
                }
            }
        }

        return count;
    }


    public static void main(String[] args) {
        GameOfLifeM obj = new GameOfLifeM();

        int[][] matrix = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        obj.gameOfLife(matrix);


    }
}
