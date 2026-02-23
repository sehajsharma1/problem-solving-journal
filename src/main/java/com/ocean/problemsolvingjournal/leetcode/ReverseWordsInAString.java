package com.ocean.problemsolvingjournal.leetcode;

/*Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

 */
public class ReverseWordsInAString {
    public static void main(String[] args) {
        String s = "a good   example";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                while (i >= 0 && s.charAt(i) != ' ') {
                    sb.append(s.charAt(i));
                    --i;
                }
                sb.append(' ');
            }
        }
        for (int i = 0, j = 0; i < sb.length(); i++, j++) {
            if (sb.charAt(i) == ' ') {
                reverseBuilder(i - j, i - 1, sb);
                j = -1;

            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }

    private static void reverseBuilder(int startIndex, int endIndex, StringBuilder builder) {
        int mid = (startIndex + endIndex) / 2;
        for (int i = startIndex, j = endIndex; i <= mid && j >= mid; i++, j--) {
            char temp = builder.charAt(i);
            builder.setCharAt(i, builder.charAt(j));
            builder.setCharAt(j, temp);
        }

    }
}
