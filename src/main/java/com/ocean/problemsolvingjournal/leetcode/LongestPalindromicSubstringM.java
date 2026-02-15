package com.ocean.problemsolvingjournal.leetcode;

/*
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
*/
public class LongestPalindromicSubstringM {

    public String longestPalindrome(String s) {
        int size = s.length();

        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j + i <= size - 1; j++) {
                int left = j;
                int right = j + i;
                boolean isPalin = true;
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        isPalin = false;
                        break;
                    }
                    left++;
                    right--;
                }
                if (isPalin) {
                    return s.substring(j, j + i + 1);
                }
            }
        }
        return "";
    }

    public String longestPalindromeV1(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        String ans = "";

        // length = 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            ans = s.substring(i, i + 1);
        }

        // length >= 2
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;

                        if (len > ans.length()) {
                            ans = s.substring(i, j + 1);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstringM solution = new LongestPalindromicSubstringM();
        String str = "babad";
        String result = solution.longestPalindromeV1(str);
        System.out.println("longest substring:" + result);

    }

}
