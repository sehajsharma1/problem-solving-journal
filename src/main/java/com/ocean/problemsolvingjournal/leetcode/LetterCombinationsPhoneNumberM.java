package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
*/
public class LetterCombinationsPhoneNumberM {

    String[] map = {
            "", "", "abc", "def",
            "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        result.add(""); // start with empty combination

        for (char d : digits.toCharArray()) {
            List<String> temp = new ArrayList<>();
            String letters = map[d - '0'];

            for (String s : result) {
                for (char c : letters.toCharArray()) {
                    temp.add(s + c);
                }
            }
            result = temp;
        }
        return result;
    }

    public List<String> letterCombinationsV1(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;

        dfs(digits, 0, new StringBuilder(), result);
        return result;
    }

    /*
    Backtracking is an algorithmic problem-solving technique where a solution is built step by step by trying all possible choices, and whenever a choice leads to an invalid state or a completed solution, the algorithm reverses (backtracks) that choice to explore other options. For example, while generating all letter combinations for a phone number like "23", the algorithm picks 'a' for 2, then tries 'd', 'e', 'f' for 3 to form "ad", "ae", "af", and after each combination, it removes the last letter and tries the next one, ensuring all valid combinations are explored efficiently.
    */
    private void dfs(String digits, int index, StringBuilder path, List<String> result) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(digits, index + 1, path, result);
            path.deleteCharAt(path.length() - 1); // backtrack
        }
    }

    // Added main method to invoke letterCombinations
    public static void main(String[] args) {
        LetterCombinationsPhoneNumberM solver = new LetterCombinationsPhoneNumberM();
        List<String> combos = solver.letterCombinationsV1("23");
        System.out.println(combos);
    }
}
