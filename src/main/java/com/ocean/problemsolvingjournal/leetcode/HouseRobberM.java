package com.ocean.problemsolvingjournal.leetcode;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
*/
public class HouseRobberM {

    public int rob(int[] nums) {
        int rob = 0;   // max money if current house is robbed
        int skip = 0;  // max money if current house is skipped

        for (int money : nums) {
            int newRob = skip + money;
            int newSkip = Math.max(skip, rob);

            rob = newRob;
            skip = newSkip;
        }
        return Math.max(rob, skip);
    }

    public static void main(String[] args) {
        HouseRobberM rob = new HouseRobberM();
        int[] houses1 = {1, 2, 3, 1};
        int[] houses2 = {2, 7, 9, 3, 1};
        int[] houses3 = {10, 1, 1, 10};

        System.out.println("Max money robbed: " + rob.rob(houses1)); // 4
        System.out.println("Max money robbed: " + rob.rob(houses2)); // 12
        System.out.println("Max money robbed: " + rob.rob(houses3)); // 20
    }
}
