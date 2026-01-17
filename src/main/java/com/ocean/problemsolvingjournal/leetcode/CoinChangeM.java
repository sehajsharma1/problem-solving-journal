package com.ocean.problemsolvingjournal.leetcode;

import java.util.Arrays;
/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
*/
public class CoinChangeM {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // acts as infinity
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    // added main to demonstrate usage
    public static void main(String[] args) {
        // sample input
        int[] coins = {1, 2, 5};
        int amount = 11;
        CoinChangeM solver = new CoinChangeM();
        int result = solver.coinChange(coins, amount);
        System.out.println("coins: " + Arrays.toString(coins) + ", amount: " + amount + " -> min coins: " + result);
    }
}
