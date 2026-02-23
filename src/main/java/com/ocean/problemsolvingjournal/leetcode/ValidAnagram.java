package com.ocean.problemsolvingjournal.leetcode;

/*Given two strings s and t, return true if t is an anagram of s, and false otherwise.



Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

Output: false*/
public class ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        ValidAnagram solution = new ValidAnagram();
        System.out.println(solution.isAnagram(s, t));
        System.out.println(solution.isAnagramV1(s, t));
    }

    public boolean isAnagram(String s, String t) {
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        int[] position = new int[128];
        if (charS.length != charT.length)
            return false;
        for (int i = 0; i < charS.length; i++) {
            position[charS[i]] = position[charS[i]] + 1;
            position[charT[i]] = position[charT[i]] - 1;

        }
        for (int value : position) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagramV1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int count[] = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }
        for (int i : count) {
            if (i != 0)
                return false;
        }
        return true;
    }
}
