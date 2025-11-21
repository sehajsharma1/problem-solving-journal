package com.ocean.problemsolvingjournal.leetcode;

import java.util.HashSet;
/*
Given a string s, find the length of the longest substring without duplicate characters.
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
*/
public class LongestSubstringWithoutRepeatingCharactersM {

    public int lengthOfLongestSubstring(String s) {
        int left = 0, maxLength = 0;
        HashSet<Character> set = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(ch);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringV1(String s) {
        boolean[] arr = new boolean[128];
        int maxLen = 0, r = 0, l = 0;
        while (r < s.length()){
            if (!arr[s.charAt(r)]){
                arr[s.charAt(r)] = true;
                maxLen = Math.max(maxLen, r - l + 1);
                r++;
            }else{
                arr[s.charAt(l)] = false;
                l++;
            }
        }
        return maxLen;

    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharactersM obj = new LongestSubstringWithoutRepeatingCharactersM();
        String input = "pwwkew";
        int result = obj.lengthOfLongestSubstring(input);
        int resultV1 = obj.lengthOfLongestSubstringV1(input);
        System.out.println("Length of longest substring: " + result);
        System.out.println("Length of longest substring: " + resultV1);

    }
}
