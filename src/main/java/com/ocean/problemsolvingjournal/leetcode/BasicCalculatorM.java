package com.ocean.problemsolvingjournal.leetcode;

import java.util.Stack;

/*
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:

Input: s = "1 + 1"
Output: 2
*/
public class BasicCalculatorM {
    public static void main(String[] args) {

        BasicCalculatorM obj = new BasicCalculatorM();
        String s = "8+9-1+(1+(4+5+2)-3)+(6+8)";
        System.out.println(obj.calculate(s));
    }

    public int calculate(String s) {
        int result = 0;
        int number = 0;
        int sign = 1;

        Stack<Integer> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            } else if (ch == '+') {
                result = result + sign * number;
                number = 0;
                sign = 1;
            } else if (ch == '-') {
                result = result + sign * number;
                number = 0;
                sign = -1;
            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                result = result + sign * number;
                number = 0;
                result = result * stack.pop();
                result = result + stack.pop();
            }
        }
        result = result + sign * number;
        return result;
    }
}
