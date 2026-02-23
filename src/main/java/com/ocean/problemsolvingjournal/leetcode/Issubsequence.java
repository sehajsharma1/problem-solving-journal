package com.ocean.problemsolvingjournal.leetcode;

/*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false

*/
class Issubsequence {
    public static void main(String[] args) {
        Issubsequence solution = new Issubsequence();
        String s = "aec";
        String t = "abcde";
        System.out.println(solution.isSubsequenceV1(s, t));
    }

    public boolean isSubsequenceV1(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        int s_itr = 0;
        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();

        for (int i = 0; i < t_chars.length; i++) {
            if (s_itr < s_chars.length && t_chars[i] == s_chars[s_itr]) {
                s_itr++;
            }
        }
        return s_itr == s_chars.length;
    }
}
