package com.ocean.problemsolvingjournal.leetcode;

import java.util.*;

/*
You are given a string s and an array of strings words. All the strings of words are of the same length.
A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.

Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
*/
public class SubstringWithConcatenationOfAllWordsH {

    public List<Integer> findSubstring(String s, String[] words) {
        boolean[] used = new boolean[words.length];
        int totalLength = words[0].length() * words.length;
        int loopSize = s.length() - totalLength;
        Set<String> result = new HashSet<>();
        getPermutationOfWords(words, used, result, new ArrayList<>());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= loopSize; i++) {

            String str = s.substring(i, i + totalLength);
            if (result.contains(str)) {
                list.add(i);
            }

        }
        return list;
    }

    public void getPermutationOfWords(String[] words, boolean[] used, Set<String> result, List<String> path) {
        if (path.size() == words.length) {
            result.add(String.join("", path));
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(words[i]);
            getPermutationOfWords(words, used, result, path);
            path.remove(path.size() - 1); // backtrack
            used[i] = false;
        }

    }

    public List<Integer> findSubstringV1(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        if (s == null || s.isEmpty() || words.length == 0)
            return result;

        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        int wordLen = words[0].length();
        int wordCountSize = words.length;

        for (int i = 0; i < wordLen; i++) {

            int left = i;
            int count = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = i; right + wordLen <= s.length(); right += wordLen) {

                String word = s.substring(right, right + wordLen);

                if (wordCount.containsKey(word)) {

                    window.put(word, window.getOrDefault(word, 0) + 1);
                    count++;

                    while (window.get(word) > wordCount.get(word)) {

                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == wordCountSize) {
                        result.add(left);
                    }

                } else {
                    window.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWordsH solution = new SubstringWithConcatenationOfAllWordsH();
        String s = "wordgoodgoodgoodbestword";
        String[] arr = new String[]{"word", "good", "best", "good"};
        System.out.println(solution.findSubstringV1(s, arr));

    }
}
