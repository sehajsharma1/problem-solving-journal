package com.ocean.problemsolvingjournal.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
*/
public class WordBreakM {
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // Optimization: find max word length
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0 && i - j <= maxLen; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // early exit
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {

        WordBreakM solution = new WordBreakM();

        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");

        boolean result = solution.wordBreak(s, wordDict);

        System.out.println("Can the string be segmented? " + result);
    }


}
