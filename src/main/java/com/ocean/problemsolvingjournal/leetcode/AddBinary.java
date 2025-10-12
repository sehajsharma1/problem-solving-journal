package com.ocean.problemsolvingjournal.leetcode;

/*Given two binary strings a and b, return their sum as a binary string.
Example 1:
Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
*/
public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            sb.append(sum % 2);
            carry = sum / 2;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinary ab = new AddBinary();
        String a = "110010";
        String b = "10111";
        String result = ab.addBinary(a, b);
        System.out.println("Sum of " + a + " and " + b + " is: " + result);
    }
}
