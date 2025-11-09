package com.ocean.problemsolvingjournal.leetcode;

/*
Given an integer x, return true if x is a palindrome, and false otherwise.
Example 1:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
*/
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        String num = String.valueOf(x);
        for (int i = 0, j = num.length() - 1; i <= j; i++, j--) {

            if (num.charAt(i) != num.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeV1(int x) {
        if (x < 0) {
            return false;
        }
        int original = x;
        int reversed = 0;
        while (x > 0) {
            reversed = reversed * 10 + x % 10;
            x = x / 10;
        }
        return original == reversed;
    }

    public static void main(String[] args) {
        PalindromeNumber pn = new PalindromeNumber();
        int[] tests = {121, -121, 10, 12321};

        for (int t : tests) {
            try {
                boolean result = pn.isPalindrome(t);
                boolean resultV1 = pn.isPalindromeV1(t);
                System.out.println("isPalindrome(" + t + ") = " + result);
                System.out.println("isPalindrome(" + t + ") = " + resultV1);
            } catch (UnsupportedOperationException e) {
                System.out.println("isPalindrome(" + t + ") not implemented: " + e.getMessage());
            }
        }
    }

}
