package com.ocean.problemsolvingjournal.leetcode;

import java.util.List;

/*
Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
*/
public class TriangleM {

    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        if (size == 1) {
            return triangle.get(0).get(0);
        }
        int[] dp = new int[size];
        List<Integer> list = triangle.get(size - 1);
        for (int i = 0; i < size; i++) {
            dp[i] = list.get(i);
        }
        for (int i = size - 2; i >= 0; i--) {

            List<Integer> row = triangle.get(i);

            for (int r = 0; r < row.size(); r++) {

                dp[r] = row.get(r) + Math.min(dp[r], dp[r + 1]);

            }

        }
        return dp[0];

    }

    public static void main(String[] args) {
        TriangleM triangle = new TriangleM();
        List<List<Integer>> list = List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3));
        int minimumTotal = triangle.minimumTotal(list);
        System.out.println(minimumTotal);
    }


}
