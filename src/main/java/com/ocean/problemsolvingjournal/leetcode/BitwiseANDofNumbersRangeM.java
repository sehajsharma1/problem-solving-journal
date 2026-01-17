package com.ocean.problemsolvingjournal.leetcode;

/*
Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.

Example 1:
Input: left = 5, right = 7
Output: 4
*/
public class BitwiseANDofNumbersRangeM {
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }

    public int rangeBitwiseAndV1(int left, int right) {
        while (right > left) {
            right = right & (right - 1);
        }
        return right;
    }

    public static void main(String[] args) {
        int left = 1;
        int right = 2147483647;
        BitwiseANDofNumbersRangeM range = new BitwiseANDofNumbersRangeM();
        int result = range.rangeBitwiseAnd(left, right);
        System.out.println(result);
    }


}

