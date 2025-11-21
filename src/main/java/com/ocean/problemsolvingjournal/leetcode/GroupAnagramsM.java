package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.
Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
*/
public class GroupAnagramsM {

    public static void main(String[] args) {
        GroupAnagramsM obj = new GroupAnagramsM();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = obj.groupAnagrams(input);
        List<List<String>> resultV1 = obj.groupAnagramsV1(input);
        System.out.println(result);
        System.out.println(resultV1);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            map.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagramsV1(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26]; // for 'a' to 'z'
            for (char c : s.toCharArray()) {
                count[c - 'a']++; // map char to index 0-25
            }
            String key = Arrays.toString(count);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }


}
