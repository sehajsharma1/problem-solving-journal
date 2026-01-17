package com.ocean.problemsolvingjournal.leetcode;

/*Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000
*/

class PowM {
    public double myPow(double x, int n) {
        long power = n;
        double result = 1.0;

        if (power < 0) {
            x = 1 / x;         // x^(-n) = 1 / x^n
            power = -power;
        }

        while (power > 0) {
            if ((power & 1) == 1) {  // if power is odd
                result *= x;
            }
            x *= x;            // square the base
            power >>= 1;       // divide exponent by 2
        }

        return result;
    }

    public static void main(String[] args) {
        PowM pow = new PowM();

        double a = 2.0;
        int n1 = 10;
        System.out.println(a + "^" + n1 + " = " + pow.myPow(a, n1));

        double b = 2.1;
        int n2 = 3;
        System.out.println(b + "^" + n2 + " = " + pow.myPow(b, n2));

        double c = 2.0;
        int n3 = -2;
        System.out.println(c + "^" + n3 + " = " + pow.myPow(c, n3));

        // edge case: large negative exponent (Integer.MIN_VALUE)
        double d = 2.0;
        int n4 = Integer.MIN_VALUE;
        System.out.println(d + "^" + n4 + " = " + pow.myPow(d, n4));
    }
}

