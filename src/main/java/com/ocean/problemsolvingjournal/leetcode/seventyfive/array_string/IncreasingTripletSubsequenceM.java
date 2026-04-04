package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

/*
Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

Example 1:
Input: nums = [1,2,3,4,5]
Output: true
*/
public class IncreasingTripletSubsequenceM {

    public boolean increasingTriplet(int[] nums) {
        int pre = nums[0], mid = -1;
        for (int i = 0; i < nums.length - 2; i++) {
            if (pre > nums[i]) {
                pre = nums[i];
            }
            if (nums[i + 1] > pre) {
                mid = nums[i + 1];
            }
            if (mid != -1 && nums[i + 2] > mid) {
                return true;
            }
        }
        return false;
    }

    public boolean increasingTripletV1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int no : nums) {
            if (no <= first) {
                first = no;
            } else if (no <= second) {
                second = no;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IncreasingTripletSubsequenceM solution = new IncreasingTripletSubsequenceM();
        int[] nums = new int[]{1, 5, 0, 4, 1, 3};
        System.out.println(solution.increasingTripletV1(nums));


    }

}
