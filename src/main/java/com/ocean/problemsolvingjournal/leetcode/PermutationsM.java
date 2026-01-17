package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.List;
/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
*/
public class PermutationsM {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used,
                           List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            path.add(nums[i]);

            backtrack(nums, used, path, result);

            path.remove(path.size() - 1); // backtrack
            used[i] = false;
        }
    }

    // Added main method to call combine
    public static void main(String[] args) {
        // Sample input
        int[] nums = {1, 2, 3};

        // Instantiate and call permute
        PermutationsM solver = new PermutationsM();
        List<List<Integer>> result = solver.permute(nums);

        // Print results
        System.out.println(result);
    }
}
