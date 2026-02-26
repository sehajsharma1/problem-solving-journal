package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

/*
Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

Example 1:
Input: s = "IceCreAm"
Output: "AceCreIm"
*/
public class ReverseVowelsOfAStringE {
    public String reverseVowels(String s) {

        boolean[] isVowel = new boolean[128];

        for (char ch : "aeiouAEIOU".toCharArray()) {
            isVowel[ch] = true;
        }
        char[] arr = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (!isVowel[arr[left]]) {
                left++;
                continue;
            } else if (!isVowel[arr[right]]) {
                right--;
                continue;
            }
            char temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        ReverseVowelsOfAStringE solution = new ReverseVowelsOfAStringE();
        String input = "IceCreAm";
        System.out.println(solution.reverseVowels(input));
    }
}
