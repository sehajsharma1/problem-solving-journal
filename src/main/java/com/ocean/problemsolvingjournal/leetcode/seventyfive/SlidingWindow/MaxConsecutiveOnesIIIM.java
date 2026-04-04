package com.ocean.problemsolvingjournal.leetcode.seventyfive.SlidingWindow;

/*
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
*/
public class MaxConsecutiveOnesIIIM {

    public static void main(String[] args) {
        MaxConsecutiveOnesIIIM obj = new MaxConsecutiveOnesIIIM();
        int[] number = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        System.out.println(obj.longestOnes(number, k));
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {

            // If current element is 0, increase zero count
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zeros exceed k, shrink window from left
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
