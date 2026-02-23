package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

/*
You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.
Return the merged string.

Example 1:
Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
*/
public class MergeStringsAlternatelyE {

    public String mergeAlternately(String word1, String word2) {
        int i = 0;
        int word1Length = word1.length();
        int word2Length = word2.length();
        int maxLength = Math.max(word1Length, word2Length);
        StringBuilder sb = new StringBuilder();
        while (i < maxLength) {
            if (i < word1Length) {
                sb.append(word1.charAt(i));
            }
            if (i < word2Length) {
                sb.append(word2.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }

    // it is faster
    public String mergeAlternatelyV1(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        char[] arr = new char[n1 + n2];
        int max = Math.max(n1, n2);
        for (int i = 0, p = 0; i < max; i++) {
            if (i < n1) {
                arr[p] = word1.charAt(i);
                p++;
            }
            if (i < n2) {
                arr[p] = word2.charAt(i);
                p++;
            }

        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String str = "abcdef";
        String str1 = "pqr";
        MergeStringsAlternatelyE solution = new MergeStringsAlternatelyE();
        System.out.println(solution.mergeAlternatelyV1(str, str1));
    }
}
