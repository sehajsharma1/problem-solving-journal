package com.ocean.problemsolvingjournal.leetcode;
/*
A peak element is an element that is strictly greater than its neighbors.
Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [1,2,3,1]
Output: 2
*/
public class FindPeakElementM {
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // or high (both same)
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4}; // sample input
        FindPeakElementM solver = new FindPeakElementM();
        int peakIndex = solver.findPeakElement(nums);
        System.out.println("Peak index: " + peakIndex);
    }
}
