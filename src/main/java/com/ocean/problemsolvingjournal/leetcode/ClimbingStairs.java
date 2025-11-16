package com.ocean.problemsolvingjournal.leetcode;

/*You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/
public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        int n = 5; // Example input
        int result = cs.climbStairs(n);
        System.out.println("Ways to climb " + n + " stairs: " + result);
    }

    public int climbStairs(int n) {
        if (n <= 2) return n;
        long prev1 = 1;
        long prev2 = 2;
        long current = 0;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            prev1 = prev2;
            prev2 = current;
        }
        return (int) current;
    }
}
