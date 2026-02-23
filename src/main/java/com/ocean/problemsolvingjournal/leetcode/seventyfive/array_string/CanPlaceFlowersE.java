package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

/*
You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
*/
public class CanPlaceFlowersE {

    public static void main(String[] args) {
        int[] flowerbed = new int[]{0, 0};
        int n = 2;
        CanPlaceFlowersE solution = new CanPlaceFlowersE();
        System.out.println(solution.canPlaceFlowers(flowerbed, n));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;
        if (length == 1 && flowerbed[0] == 0) {
            return true;
        }
        for (int i = 0; i < length && n > 0; i++) {
            if (flowerbed[i] != 1) {
                if (i == 0 && flowerbed[i + 1] != 1) {
                    flowerbed[i] = 1;
                    n--;
                } else if (i == length - 1 && flowerbed[i - 1] != 1) {
                    flowerbed[i] = 1;
                    n--;
                } else if ((i + 1) < length && flowerbed[i + 1] != 1 && flowerbed[i - 1] != 1) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        return n == 0;
    }
}
