package com.ocean.problemsolvingjournal.leetcode;

import java.util.Stack;

/*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.


Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Example 5:

Input: s = "([)]"

Output: false*/
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // Push expected closing bracket
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                // If stack empty or top doesn't match current char
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        // Valid only if all brackets matched
        return stack.isEmpty();
    }

    public boolean isValidV1(String s) {
        char stack[] = new char[s.length()];
        int top = -1;

        for (char str : s.toCharArray()) {
            if (str == '(' || str == '{' || str == '[') {
                stack[++top] = str;
            } else {
                if (top < 0) {
                    return false;
                }
                if (str == ')' && stack[top--] != '(') {
                    return false;
                } else if (str == ']' && stack[top--] != '[') {
                    return false;
                }
                if (str == '}' && stack[top--] != '{') {
                    return false;
                }
            }
        }
        return top == -1;
    }

    public static void main(String[] args) {
        ValidParentheses checker = new ValidParentheses();
        System.out.println(checker.isValid("()[]{}"));
        System.out.println(checker.isValidV1("({[]})"));
    }
}
