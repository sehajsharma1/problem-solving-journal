package com.ocean.problemsolvingjournal.leetcode;

import java.util.HashMap;
/*Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true*/
public class ContainsDuplicate2 {
    public static void main(String[] args) {
        ContainsDuplicate2 containsDuplicate2 = new ContainsDuplicate2();
        int[] nums = new int[]{1, 0, 1, 1};
        System.out.println(containsDuplicate2.containsNearbyDuplicate(nums, 1));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (map.containsKey(temp)) {
                int diff = Math.abs(i - map.get(temp));
                if (diff <= k)
                    return true;
            }
            map.put(temp, i);
        }
        return false;
    }
}
