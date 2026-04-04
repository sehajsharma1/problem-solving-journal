package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

/*
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
*/
public class ProductOfArrayExceptSelfM {

    /**
     * Pi = product of elements before index i.
     * Si = product of elements after index i.
     * answer[i] = Pi × Si.
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        //Prefix
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        //Suffix
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        ProductOfArrayExceptSelfM solution = new ProductOfArrayExceptSelfM();
        for (int number : solution.productExceptSelf(nums)) {
            System.out.println(number);
        }
    }
}
