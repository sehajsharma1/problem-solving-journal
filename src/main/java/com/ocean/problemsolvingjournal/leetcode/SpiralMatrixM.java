package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
Given an m x n matrix, return all elements of the matrix in spiral order.
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
*/
public class SpiralMatrixM {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // top row
            for (int c = left; c <= right; c++)
                res.add(matrix[top][c]);
            top++;

            // right column
            for (int r = top; r <= bottom; r++)
                res.add(matrix[r][right]);
            right--;

            // bottom row
            if (top <= bottom) {
                for (int c = right; c >= left; c--)
                    res.add(matrix[bottom][c]);
                bottom--;
            }

            // left column
            if (left <= right) {
                for (int r = bottom; r >= top; r--)
                    res.add(matrix[r][left]);
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SpiralMatrixM solver = new SpiralMatrixM();

        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println("Spiral order of matrix1: " + solver.spiralOrder(matrix1));
        System.out.println("Spiral order of matrix2: " + solver.spiralOrder(matrix2));
    }
}

