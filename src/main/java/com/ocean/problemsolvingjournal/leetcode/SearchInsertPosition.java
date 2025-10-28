package com.ocean.problemsolvingjournal.leetcode;

/*
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
You must write an algorithm with O(log n) runtime complexity.
Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2
*/

public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {

        return searchInsertHelper(nums, 0, nums.length - 1, target);
    }

    public int searchInsertHelper(int[] nums, int left, int right, int target) {

        if (left > right) {
            return left;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        }
        if (target > nums[mid]) {
            return searchInsertHelper(nums, mid + 1, right, target);
        } else {
            return searchInsertHelper(nums, left, mid - 1, target);
        }


    }

    public static void main(String[] args) {
        SearchInsertPosition sip = new SearchInsertPosition();
        int[] nums = {1, 3, 5, 6, 7};
        int target = 4;
        int result = sip.searchInsert(nums, target);
        System.out.println("Insert position: " + result);
    }
}
