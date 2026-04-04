package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string.TwoPointers;

/*
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Example 1:

Input: height = [1,1]
Output: 1
*/
public class ContainerWithMostWaterM {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxDistance = 0;
        while (left <= right) {
            int min = Math.min(height[left], height[right]);
            maxDistance = Math.max(maxDistance, min * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }

        }
        return maxDistance;
    }

    public int maxAreaV1(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            int area = h * w;
            max = Math.max(max, area);
            while (left < right && height[left] <= h) {
                left++;
            }
            while (left < right && height[right] <= h) {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ContainerWithMostWaterM obj = new ContainerWithMostWaterM();
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(obj.maxAreaV1(height));
    }
}
