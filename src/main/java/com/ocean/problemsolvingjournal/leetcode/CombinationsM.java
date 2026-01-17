package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.List;
/*
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
*/
public class CombinationsM {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int start, int n, int k,
                           List<Integer> path,
                           List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // Pruning: stop early if not enough numbers left
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtrack(i + 1, n, k, path, res);
            path.remove(path.size() - 1); // backtrack
        }
    }
    // Added main method to call combine
    public static void main(String[] args) {
        CombinationsM solver = new CombinationsM();
        int n = 4;
        int k = 2;
        List<List<Integer>> combinations = solver.combine(n, k);
        System.out.println("Combinations of " + n + " choose " + k + ": " + combinations);
    }
}
