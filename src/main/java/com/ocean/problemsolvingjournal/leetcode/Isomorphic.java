package com.ocean.problemsolvingjournal.leetcode;

/*
Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.



Example 1:

Input: s = "egg", t = "add"

Output: true

Explanation:

The strings s and t can be made identical by:

Mapping 'e' to 'a'.
Mapping 'g' to 'd'.
* */
public class Isomorphic {

    public static void main(String[] args) {
        String s = "paper";
        String t = "title";
        Isomorphic solution = new Isomorphic();
        System.out.println(solution.isIsomorphic(s, t));
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] sMap = new int[256];
        int[] tMap = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (sMap[sChar] != tMap[tChar]) {
                return false;
            }
            // Map characters to their indices
            sMap[sChar] = i + 1; // Use i + 1 to avoid zero index confusion
            tMap[tChar] = i + 1;
        }
        return true;
    }
}
