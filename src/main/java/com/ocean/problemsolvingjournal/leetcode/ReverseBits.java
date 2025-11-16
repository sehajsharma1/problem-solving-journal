package com.ocean.problemsolvingjournal.leetcode;

/*
Reverse bits of a given 32 bits signed integer.
Example 1:
Input: n = 43261596
Output: 964176192
*/
public class ReverseBits {
    public int reverseBits(int n) {
        int reversedResult = 0;
        for (int i = 0; i < 32; i++) {
            reversedResult = reversedResult << 1;
            reversedResult = reversedResult | (n & 1);
            n = n >>> 1;
        }
        return reversedResult;
    }

    public static void main(String[] args) {
        ReverseBits reverseBitsInstance = new ReverseBits();
        int originalNumber = 6; // example input
        int reversedNumber = reverseBitsInstance.reverseBits(originalNumber);
        System.out.println("Original: " + originalNumber);
        System.out.println("Reversed: " + reversedNumber);
    }
}
