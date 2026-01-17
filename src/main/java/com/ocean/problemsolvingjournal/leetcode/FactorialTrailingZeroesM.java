package com.ocean.problemsolvingjournal.leetcode;
/*
Given an integer n, return the number of trailing zeroes in n!.
Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

Example 1:
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.

*/
public class FactorialTrailingZeroesM {

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            n /= 5;        // count factors of 5, 25, 125, ...
            count += n;
        }
        return count;
    }

    public static void main(String[] args) {
        FactorialTrailingZeroesM solver = new FactorialTrailingZeroesM();
        // sample tests
        int[] tests = {25};
        for (int n : tests) {
            System.out.printf("n=%d -> trailing zeroes=%d%n", n, solver.trailingZeroes(n));
        }
    }
}
