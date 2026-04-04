package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string.TwoPointers;

/*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true
*/
public class IsSubsequenceE {
    public boolean isSubsequence(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int i = 0, j = 0;
        int m = 1;
        if (s.isEmpty() && t.isEmpty()) {
            return true;
        }
        while (j < s.length() && i < t.length()) {
            if (arr2[i] == arr1[j]) {
                j++;
            }
            if (m < t.length() && arr2[m] == arr1[j]) {
                j++;
            }
            i = i + 2;
            m = m + 2;
        }
        return j == s.length();
    }

    public boolean isSubsequenceV1(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int j = 0;
        for (char c : arr1) {
            boolean flag = false;
            while (j < arr2.length) {
                if (arr2[j++] == c) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aza", t = "abzba";
        IsSubsequenceE result = new IsSubsequenceE();
        System.out.println(result.isSubsequenceV1(s, t));
    }
}
