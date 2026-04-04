package com.ocean.problemsolvingjournal.leetcode.seventyfive.SlidingWindow;

/*
You are given an integer array nums consisting of n elements, and an integer k.
Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

Example 1:
Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
*/
public class MaximumAverageSubarrayIE {

    public static void main(String[] args) {
        MaximumAverageSubarrayIE obj = new MaximumAverageSubarrayIE();
        int[] number = {-1};
        int k = 1;
        System.out.println(obj.findMaxAverage(number, k));
    }

    public double findMaxAverage(int[] nums, int k) {

        double avg;
        int right = 0;
        int left = 0;
        int count = 0;
        int sum = 0;
        double maxAvg = -10000.0;
        while (right < nums.length) {
            sum = sum + nums[right];
            count++;
            if (count == k) {
                avg = (double) sum / count;
                maxAvg = Math.max(avg, maxAvg);
                sum = sum - nums[left];
                left++;
                count--;
            }
            right++;
        }
        return maxAvg;
    }

    public double findMaxAverageV1(int[] nums, int k) {
        int n = nums.length;

        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        long maxSum = windowSum;

        for (int i = k; i < n; i++) {
            windowSum += nums[i];
            windowSum -= nums[i - k];

            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k;
    }
}
