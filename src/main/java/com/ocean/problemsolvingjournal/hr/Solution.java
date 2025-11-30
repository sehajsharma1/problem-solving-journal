package com.ocean.problemsolvingjournal.hr;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 2;
        rotate(nums, k);
    }

    public static void rotate(int[] nums, int k) {
        int numsLength = nums.length;
        k = k % numsLength;
        reverseArr(nums, 0, numsLength);
        reverseArr(nums, 0, k);
        reverseArr(nums, k, numsLength);
    }

    public static void reverseArr(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[--end];
            nums[end] = nums[start];
            nums[start++] = temp;
        }
    }
}
