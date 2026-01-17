package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
*/
public class CombinationSumM {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);  // important optimization
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start,
                           List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // pruning

            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, path, result);
            path.remove(path.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        CombinationSumM solver = new CombinationSumM();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = solver.combinationSum(candidates, target);
        System.out.println(result);
    }
}
