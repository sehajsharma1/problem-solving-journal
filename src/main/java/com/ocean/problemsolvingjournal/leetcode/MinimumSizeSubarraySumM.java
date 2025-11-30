package com.ocean.problemsolvingjournal.leetcode;
/*
Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
*/
public class MinimumSizeSubarraySumM {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        MinimumSizeSubarraySumM solution = new MinimumSizeSubarraySumM();
        System.out.println(solution.minSubArrayLen(7, nums));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int minWindowSize = Integer.MAX_VALUE;
        int left = 0;
        int rollingSum = 0;
        for (int right = 0; right < nums.length; right++) {
            rollingSum += nums[right];

            while (rollingSum >= target) {
                minWindowSize = Math.min(minWindowSize, right - left + 1);
                rollingSum -= nums[left];
                left++;
            }
        }
        return minWindowSize == Integer.MAX_VALUE ? 0 : minWindowSize;
    }
}
