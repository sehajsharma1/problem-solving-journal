package com.ocean.problemsolvingjournal.leetcode;

/*
Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,3,2]
Output: 3
*/
public class SingleNumberIIM {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;

        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        return ones;    // the unique element
    }

    public static void main(String[] args) {
        SingleNumberIIM solver = new SingleNumberIIM();

        int[] nums1 = {2, 2, 3, 2};
        System.out.println("Unique in nums1: " + solver.singleNumber(nums1)); // expected 3

        int[] nums2 = {0,1,0,1,0,1,99};
        System.out.println("Unique in nums2: " + solver.singleNumber(nums2)); // expected 99
    }
}
