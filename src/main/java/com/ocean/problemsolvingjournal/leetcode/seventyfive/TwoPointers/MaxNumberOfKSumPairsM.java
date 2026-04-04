package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string.TwoPointers;

import java.util.Arrays;

public class MaxNumberOfKSumPairsM {

    // time limit exceeded
    public int maxOperations(int[] nums, int k) {
        int operation = 0;
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] == 0) {
                i = i + 1;
                continue;
            }
            int pointer = i + 1;
            for (int j = pointer; j < nums.length; j++) {
                if ((nums[i] != 0 && nums[j] != 0 && (nums[i] + nums[j]) == k)) {
                    operation++;
                    nums[i] = 0;
                    nums[j] = 0;
                }
                if ((j + 1 < nums.length) && (nums[pointer] != 0 && nums[j + 1] != 0) && (nums[pointer] + nums[j + 1]) == k) {
                    operation++;
                    nums[i + 1] = 0;
                    nums[j + 1] = 0;
                }
            }
            i = i + 2;
        }
        return operation;

    }
    public int maxOperationsV1(int[] nums, int k) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= k) nums[i] = 0;
        }
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int count = 0;
        while(left < right){
            int sum = nums[left] + nums[right];
            if(sum == k){
                count += 1;
                left++;
                right--;
            }
            else if(sum > k){
                right--;
            }
            else {
                left++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 4, 4, 1, 3, 4, 4, 1, 4, 4, 1, 2, 1, 2, 2, 3, 2, 4, 2};
        int k = 3;
        MaxNumberOfKSumPairsM obj = new MaxNumberOfKSumPairsM();
        System.out.println(obj.maxOperationsV1(nums, k));
    }
}
