package com.ocean.problemsolvingjournal.leetcode;

import java.util.Stack;

/*
You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
Evaluate the expression. Return an integer that represents the value of the expression.

Note that:
The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.

Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
*/
public class EvaluateReversePolishNotationM {

    public static void main(String[] args) {
        EvaluateReversePolishNotationM obj = new EvaluateReversePolishNotationM();
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(obj.evalRPN(tokens));
        System.out.println(obj.evalRPNV1(tokens));

    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String c : tokens) {
            if (c.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (c.equals("-")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first - second);
            } else if (c.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (c.equals("/")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first / second);
            } else {
                stack.push(Integer.parseInt(c));
            }
        }

        return stack.peek();

    }

    public int evalRPNV1(String[] t) {
        int[] st = new int[t.length];
        int i = 0;
        for (String s : t) {
            char c = s.charAt(0);
            if (c == '+') {
                int b = st[--i], a = st[--i];
                st[i++] = a + b;
            } else if (c == '-' && s.length() == 1) {
                int b = st[--i], a = st[--i];
                st[i++] = a - b;
            } else if (c == '*') {
                int b = st[--i], a = st[--i];
                st[i++] = a * b;
            } else if (c == '/') {
                int b = st[--i], a = st[--i];
                st[i++] = a / b;
            } else {
                st[i++] = Integer.parseInt(s);
            }
        }
        return st[0];
    }
}
