package com.ocean.problemsolvingjournal.leetcode;

/*
Algo: Kadane's Algorithm.

Given an integer array nums, find the subarray with the largest sum, and return its sum.
*/
public class MaximumSubarrayM {
    public int maxSubArray(int[] nums) {
        int size = nums.length;
        int max = Integer.MIN_VALUE;
        int current = 0;
        for (int i = 0; i < size; i++) {
            current = current + nums[i];
            max = Math.max(current, max);
            if (current < 0) {
                current = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumSubarrayM solution = new MaximumSubarrayM();
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        solution.maxSubArray(nums);
    }
}
