package com.ocean.problemsolvingjournal.leetcode.seventyfive.SlidingWindow;

/*
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.


Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
*/
public class LongestSubarrayAfterDeletingOneElement {

    public int longestSubarray(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubarrayAfterDeletingOneElement sol = new LongestSubarrayAfterDeletingOneElement();
        int[] nums = {0, 1, 1, 1, 0, 1, 1, 0, 1};
        System.out.println(sol.longestSubarray(nums));
    }
}
