package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
*/
public class GenerateParenthesesM {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        StringBuilder current = new StringBuilder();

        backtrack(result, current, 0, 0, n);

        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int n) {
        if (current.length() == n * 2) {
            result.add(current.toString());
            return;
        }

        if (open < n) {
            current.append("(");
            backtrack(result, current, open + 1, close, n);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
        if (close < n) {
            current.append(")");
            backtrack(result, current, open, close + 1, n);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        GenerateParenthesesM obj = new GenerateParenthesesM();
        System.out.println(obj.generateParenthesis(3));

    }
}
