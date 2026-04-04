package com.ocean.problemsolvingjournal.leetcode.seventyfive.SlidingWindow;

/*
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
*/
public class MaximumNumberOfVowelsInASubstringOfGivenLengthM {


    public static void main(String[] args) {
        MaximumNumberOfVowelsInASubstringOfGivenLengthM obj = new MaximumNumberOfVowelsInASubstringOfGivenLengthM();
        String s = "weallloveyou";
        int k = 7;
        System.out.println(obj.maxVowels(s, k));
    }

    public int maxVowels(String s, int k) {
        boolean[] isVowel = new boolean[26];
        isVowel[0] = true;
        isVowel['e' - 'a'] = true;
        isVowel['i' - 'a'] = true;
        isVowel['o' - 'a'] = true;
        isVowel['u' - 'a'] = true;
        char[] arr = s.toCharArray();
        int arrLength = arr.length;
        int count = 0;
        int left = 0;
        for (int i = 0; i < k; i++) {
            if (isVowel[arr[i] - 'a']) {
                count++;
            }
        }
        int maxCount = count;
        for (int i = k; i < arrLength; i++) {

            if (isVowel[arr[i] - 'a']) {
                count++;
            }

            if (isVowel[arr[left] - 'a']) {
                count--;
            }

            if (count > maxCount) {
                maxCount = count;
            }
            left++;
        }
        return maxCount;
    }
}
