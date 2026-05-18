package com.ocean.problemsolvingjournal.leetcode;


/*
You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:

"1" -> 'A'

"2" -> 'B'

...

"25" -> 'Y'

"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:

"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.

Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.

The test cases are generated so that the answer fits in a 32-bit integer.



Example 1:

Input: s = "12"

Output: 2

Explanation:

"12" could be decoded as "AB" (1 2) or "L" (12).
*/
public class DecodeWaysM {

    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) return 0;

        int next = 1;
        int nextNext = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            int current = 0;

            if (s.charAt(i) != '0') {
                current += next;
            }

              if (i + 1 < s.length()) {
                int num = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                if (num >= 10 && num <= 26) {
                    current += nextNext;
                }
            }

            nextNext = next;
            next = current;
        }

        return next;
    }

    public  int numDecodingsV1(String s) {
        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int[] dp = new int[n + 1];

        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {

            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }

            dp[i] = dp[i + 1];

            if (i + 1 < n) {
                int num = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                if (num >= 10 && num <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }

        return dp[0];
    }

    public  int numDecodingsV2(String s) {
        return dfs(s, 0);
    }

    private  int dfs(String s, int i) {
        // Reached end → 1 valid way
        if (i == s.length()) return 1;

        // Starts with '0' → invalid
        if (s.charAt(i) == '0') return 0;

        // Take 1 digit
        int ways = dfs(s, i + 1);

        // Take 2 digits if valid
        if (i + 1 < s.length()) {
            int num = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
            if (num >= 10 && num <= 26) {
                ways += dfs(s, i + 2);
            }
        }

        return ways;
    }

    public static void main(String[] args) {
        DecodeWaysM obj = new DecodeWaysM();
        System.out.println(obj.numDecodingsV2("11116")); // Output: 8
    }

}
