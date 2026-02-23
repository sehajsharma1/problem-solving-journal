package com.ocean.problemsolvingjournal.leetcode;

/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
*/

public class EditDistanceM {
    int[][] dp;
    String w1, w2;

    /*
INSERT → move right (i, j+1)

DELETE → move down (i+1, j)

REPLACE → move diagonal (i+1, j+1)

MATCH → move diagonal without cost
*/
    public int minDistance(String word1, String word2) {
        this.w1 = word1;
        this.w2 = word2;

        dp = new int[word1.length()][word2.length()];

        // initialize with -1 (not computed)
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                dp[i][j] = -1;
            }
        }

        return dfs(0, 0);
    }


    private int dfs(int i, int j) {

        // base cases
        if (i == w1.length())
            return w2.length() - j;

        if (j == w2.length())
            return w1.length() - i;

        // memo check
        if (dp[i][j] != -1)
            return dp[i][j];

        // if characters match
        if (w1.charAt(i) == w2.charAt(j)) {
            dp[i][j] = dfs(i + 1, j + 1);
        } else {
            int insert = dfs(i, j + 1);
            int delete = dfs(i + 1, j);
            int replace = dfs(i + 1, j + 1);

            dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
        }

        return dp[i][j];
    }

    /*
    *
  ""   r   o   s
      -----------------
"" |    0   1   2   3
h  |    1   1   2   3
o  |    2   2   1   2
r  |    3   2   2   2
s  |    4   3   3   2
e  |    5   4   4   3

* */

    public int minDistanceV1(String word1, String word2) {
        final int m = word1.length();//first word length
        final int n = word2.length();//second word length
        // dp[i][j] := min # of operations to convert word1[0..i) to word2[0..j)
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i)
            dp[i][0] = i;

        for (int j = 1; j <= n; ++j)
            dp[0][j] = j;

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (word1.charAt(i - 1) == word2.charAt(j - 1))//same characters
                    dp[i][j] = dp[i - 1][j - 1];//no operation
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1],  //replace
                            Math.min(dp[i - 1][j],   //delete
                                    dp[i][j - 1])) + 1;  //insert

        return dp[m][n];
    }


    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        EditDistanceM solver = new EditDistanceM();

        // Call each method exactly once
        int resultRecursive = solver.minDistance(word1, word2);
        System.out.println("minDistance (recursive with memo) between \"" + word1 + "\" and \"" + word2 + "\" = " + resultRecursive);

        int resultIterative = solver.minDistanceV1(word1, word2);
        System.out.println("minDistanceV1 (iterative DP) between \"" + word1 + "\" and \"" + word2 + "\" = " + resultIterative);
    }

}
