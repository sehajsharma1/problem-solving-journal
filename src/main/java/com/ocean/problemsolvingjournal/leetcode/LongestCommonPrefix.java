package com.ocean.problemsolvingjournal.leetcode;

/*Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".*/
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = new String[]{"cir", "car"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder sb = new StringBuilder("");
        char[] firstWordCharArr = strs[0].toCharArray();
        int maximumCommonPrefix = Integer.MAX_VALUE;
        for (int i = 1; i < strs.length; i++) {
            char[] s = strs[i].toCharArray();
            int commonPrefixCount = 0;
            for (int j = 0; j < firstWordCharArr.length && j < s.length; j++) {
                if (s[j] == firstWordCharArr[j]) {
                    ++commonPrefixCount;
                } else {
                    break;
                }
            }
            if (commonPrefixCount > 0) {
                if (commonPrefixCount < maximumCommonPrefix) {
                    maximumCommonPrefix = commonPrefixCount;
                }
            } else {
                return "";
            }
        }
        for (int i = 0; i < maximumCommonPrefix; i++) {
            sb.append(firstWordCharArr[i]);
        }
        return sb.toString();
    }
}
