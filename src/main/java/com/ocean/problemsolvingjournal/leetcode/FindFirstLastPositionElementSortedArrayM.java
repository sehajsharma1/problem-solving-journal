package com.ocean.problemsolvingjournal.leetcode;

import java.util.Arrays;
/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.
Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
*/
public class FindFirstLastPositionElementSortedArrayM {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{
                boundary(nums, target, true),   // first position
                boundary(nums, target, false)   // last position
        };
    }

    private int boundary(int[] nums, int target, boolean findFirst) {
        int left = 0, right = nums.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                ans = mid;
                if (findFirst) {
                    right = mid - 1;  // move left
                } else {
                    left = mid + 1;   // move right
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FindFirstLastPositionElementSortedArrayM solver = new FindFirstLastPositionElementSortedArrayM();
        int[] nums = {1, 4};
        int target = 4;
        int[] range = solver.searchRange(nums, target);
        System.out.println(Arrays.toString(range));
    }
}
