package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

/*
For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

Example 1:
Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
*/
public class GreatestCommonDivisorOfStringsE {

    /**
     * A string can divide another string only if it repeats to form it.
     * The base string length must divide both string lengths.
     * So we find common divisors of the lengths.
     * The largest possible base length is the GCD of the lengths.
     * Example: lengths 6 and 4 → GCD = 2 → base = "AB".
     */

    public String gcdOfStrings(String str1, String str2) {
        // Step 1: Check compatibility
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // Step 2: Find GCD of lengths
        int gcdLen = gcd(str1.length(), str2.length());

        // Step 3: Return prefix of gcd length
        return str1.substring(0, gcdLen);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        String str = "TAUXXTAUXXTAUXXTAUXXTAUXX";
        String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
        GreatestCommonDivisorOfStringsE solution = new GreatestCommonDivisorOfStringsE();
        System.out.println(solution.gcdOfStrings(str, str1));

    }
}
