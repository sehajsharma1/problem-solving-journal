package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string.TwoPointers;

/*
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
*/
public class MoveZeroesE {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                int j = i + 1;
                while (j < length) {
                    if (nums[j] != 0) {
                        int num = nums[i];
                        nums[i] = nums[j];
                        nums[j] = num;
                        break;
                    }
                    j++;
                }
                if (j == length) {
                    return;
                }
            }
        }
    }

    public void moveZeroesV1(int[] nums) {
        int j = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        MoveZeroesE moveZeroesE = new MoveZeroesE();
        int[] nums = new int[]{8, 0, 1, 0, 0, 0, 0, 3, 0, 0, 0, 12};
        MoveZeroesE result = new MoveZeroesE();
        moveZeroesE.moveZeroes(nums);
    }
}
