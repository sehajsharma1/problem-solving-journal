package com.ocean.problemsolvingjournal.leetcode;

/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
*/
public class RotateImageM {

    public void rotate(int[][] matrix) {
        int rowsLength = matrix.length;
        int columnLength = matrix[0].length;

        int top = 0;
        int bottom = matrix.length - 1;

        while (top < bottom) {
            int[] temp = matrix[top];
            matrix[top] = matrix[bottom];
            matrix[bottom] = temp;
            top++;
            bottom--;
        }

        for (int r = 0; r < rowsLength; r++) {
            for (int c = r + 1; c < rowsLength; c++) {
                int tempValue = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = tempValue;
            }
        }
        System.out.println("done");
    }

    public static void main(String[] args) {
        RotateImageM obj = new RotateImageM();
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        obj.rotate(matrix);
    }
}
