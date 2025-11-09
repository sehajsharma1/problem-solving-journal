package com.ocean.problemsolvingjournal.leetcode;
/*Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.*/
public class Sqrtx {

    public static void main(String[] args) {
        Sqrtx sqrtxObj = new Sqrtx();
        int result = sqrtxObj.mySqrt(8);
        int resultV1 = sqrtxObj.mySqrtV1(8);
        System.out.println(result);
        System.out.println(resultV1);
    }

    public int mySqrt(int x) {
        int subCount = 0;
        int i = 1;
        while (x > 0) {
            x = x - i;
            i = i + 2;
            ++subCount;
        }
        if (x == 0) {
            return subCount;
        } else {
            return subCount - 1;
        }
    }

    public int mySqrtV1(int x) {
        if (x < 2) return x; // sqrt(0)=0, sqrt(1)=1
        int left = 1, right = x / 2, ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Use long to prevent overflow when mid*mid > Integer.MAX_VALUE
            long sq = (long) mid * mid;
            if (sq == x) {
                return mid;
            } else if (sq < x) {
                ans = mid;        // mid is a candidate
                left = mid + 1;   // search in the higher half
            } else {
                right = mid - 1;  // search in the lower half
            }
        }
        return ans; // floor of sqrt(x)
    }
}