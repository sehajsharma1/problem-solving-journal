package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

/*
Given an array of characters chars, compress it using the following algorithm:
Begin with an empty string s. For each group of consecutive repeating characters in chars:
If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
After you are done modifying the input array, return the new length of the array.
You must write an algorithm that uses only constant extra space.

Note: The characters in the array beyond the returned length do not matter and should be ignored.

Example 1:
Input: chars = ["a","a","b","b","c","c","c"]
Output: 6
*/
public class StringCompressionM {
    public int compress(char[] chars) {
        int loc = 0;
        for (int i = 0; i < chars.length; ) {
            int count = 0;
            chars[loc] = chars[i];
            int j = i;
            while (j < chars.length && chars[j] == chars[i]) {
                count++;
                j++;
            }
            if (count > 1) {
                char[] arr = getDigitArr(count);
                for (char c : arr) {
                    chars[++loc] = c;
                }
            }
            i = j;
            loc++;
        }
        return loc;
    }

    private char[] getDigitArr(int count) {
        int num = count;
        int temp = num;
        int length = 0;
        while (temp > 0) {
            length++;
            temp = temp / 10;
        }
        char[] arr = new char[length];
        for (int c = length - 1; c >= 0; c--) {
            arr[c] = (char) ('0' + (num % 10));
            num = num / 10;
        }
        return arr;
    }

    public static void main(String[] args) {
        String input = "abcdd";
        StringCompressionM stringCompressionM = new StringCompressionM();
        System.out.println(stringCompressionM.compress(input.toCharArray()));
    }

}
