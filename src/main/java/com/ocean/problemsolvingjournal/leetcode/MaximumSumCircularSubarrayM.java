package com.ocean.problemsolvingjournal.leetcode;

/*
Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

Example 1:
Input: nums = [1,-2,3,-2]
Output: 3
*/
public class MaximumSumCircularSubarrayM {

    private int totalSum = 0;

    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = kadane(nums, true);
        int minSum = kadane(nums, false);
        return maxSum > 0 ? Math.max(maxSum, totalSum - minSum) : maxSum;
    }

    public int kadane(int[] nums, boolean isMax) {
        int sum = nums[0];
        int curr_sum = nums[0];
        totalSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            totalSum += nums[i];
            if (isMax) {
                curr_sum = Math.max(nums[i], nums[i] + curr_sum);
                sum = Math.max(curr_sum, sum);
            } else {
                curr_sum = Math.min(nums[i], nums[i] + curr_sum);
                sum = Math.min(curr_sum, sum);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        MaximumSumCircularSubarrayM solution = new MaximumSumCircularSubarrayM();
        int[] nums = new int[]{100, 200, -1, -2, -3, -4, -5, 600, 700, -8, 2};
        solution.maxSubarraySumCircular(nums);
    }
}
