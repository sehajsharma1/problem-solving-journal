package com.ocean.problemsolvingjournal.leetcode;

import java.util.HashMap;

/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
*/
public class MinimumWindowSubstringH {

    public String minWindow(String s, String t) {

        char[] tarr = t.toCharArray();
        char[] sarr = s.toCharArray();
        int requiredFrequency = t.length();

        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : tarr) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> set = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        int tempFrequency = requiredFrequency;
        String result = "";

        for (char ch : sarr) {

            set.put(ch, set.getOrDefault(ch, 0) + 1);
            sb.append(ch);

            if (map.containsKey(ch)) {
                if (set.get(ch) <= map.get(ch)) {
                    --tempFrequency;
                }
            }

            while (tempFrequency == 0) {

                if (result.isEmpty() || sb.length() < result.length()) {
                    result = sb.toString();
                }

                char tempChar = sb.charAt(0);
                sb.deleteCharAt(0);

                set.put(tempChar, set.get(tempChar) - 1);

                if (map.containsKey(tempChar)) {
                    if (set.get(tempChar) < map.get(tempChar)) {
                        ++tempFrequency;
                    }
                }
            }
        }

        return result;
    }

    public String minWindowV1(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] freq = new int[128];
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int left = 0, right = 0;
        int required = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {
            char r = s.charAt(right);

            if (freq[r] > 0) {
                required--;
            }

            freq[r]--;
            right++;

            while (required == 0) {

                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                char l = s.charAt(left);
                freq[l]++;

                if (freq[l] > 0) {
                    required++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstringH solution = new MinimumWindowSubstringH();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(solution.minWindowV1(s, t));

    }
}
