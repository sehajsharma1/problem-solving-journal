package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

/*
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"
*/
public class ReverseWordsInAStringM {


    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int length = s.length();
        char[] finalArr = new char[length + 1];
        int x = -1;
        for (int i = length - 1; i >= 0; ) {
            if (arr[i] == ' ') {
                i--;
                continue;
            } else {
                int j = i;
                for (; j >= 0; j--) {
                    if (arr[j] == ' ') {
                        break;
                    }
                }
                for (int m = j + 1; m <= i; m++) {
                    ++x;
                    finalArr[x] = arr[m];
                }
                finalArr[++x] = ' ';
                i = j;
            }
        }
        return new String(finalArr).substring(0, x);
    }

    public static void main(String[] args) {
        ReverseWordsInAStringM solution = new ReverseWordsInAStringM();
        String input = "the sky is blue";
        System.out.println(solution.reverseWords(input));

    }
}
