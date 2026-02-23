package com.ocean.problemsolvingjournal.leetcode;

/*A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
*/
class ValidPalindrome {
    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(solution.isPalindrome(" "));
        System.out.println(solution.isPalindrome("race a car"));
        System.out.println(solution.isPalindromeV1("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0, j = chars.length - 1; i < chars.length && j >= 0; i++, j--) {
            int c1 = chars[i];
            int c2 = chars[j];
            if (Character.isLetterOrDigit(c1)) {
                sb.append(Character.toLowerCase(chars[i]));
            }
            if (Character.isLetterOrDigit(c2)) {
                sb2.append(Character.toLowerCase(chars[j]));
            }
        }
        if (sb.toString().contentEquals(sb2)) {
            return true;
        }
        return false;
    }

    public boolean isPalindromeV1(String s) {
        int i = 0;
        int j = s.length() - 1;

        char[] chars = s.toCharArray();

        while (i < j) {
            int c = lower(chars[i]);
            int d = lower(chars[j]);
            if (c == 0) {
                i++;
                continue;
            }

            if (d == 0) {
                j--;
                continue;
            }

            if (c != d) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    int lower(char c) {
        if (c >= 'a' && c <= 'z') {
            return c;
        }

        if (c >= '0' && c <= '9') {
            return c;
        }

        if (c >= 'A' && c <= 'Z') {
            return 'a' + c - 'A';
        }

        return 0;
    }
}
