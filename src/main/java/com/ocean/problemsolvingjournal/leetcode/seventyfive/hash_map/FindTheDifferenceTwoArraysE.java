package com.ocean.problemsolvingjournal.leetcode.seventyfive.hash_map;

import java.util.*;

/*
Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:

answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
Note that the integers in the lists may be returned in any order.

Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4,6]
Output: [[1,3],[4,6]]
*/
public class FindTheDifferenceTwoArraysE {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int num : nums2) {
            map.putIfAbsent(num, true);
        }
        Set<Integer> list1 = new HashSet<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                list1.add(num);
            } else {
                map.put(num, false);
            }
        }
        List<Integer> list2 = new ArrayList<>();
        map.forEach((k, v) -> {
            if (v) {
                list2.add(k);
            }
        });
        return List.of(list1.stream().toList(), list2);
    }

    public List<List<Integer>> findDifferenceV1(int[] nums1, int[] nums2) {
        int OFFSET = 1000;
        boolean[] set1 = new boolean[OFFSET * 2 + 1];
        for (int num : nums1) {
            set1[num + OFFSET] = true;
        }
        boolean[] set2 = new boolean[OFFSET * 2 + 1];
        for (int num : nums2) {
            set2[num + OFFSET] = true;
        }
        List<Integer> result1 = new ArrayList<>();
        for (int num : nums1) {
            if (set1[num + OFFSET] && !set2[num + OFFSET]) {
                result1.add(num);
                set1[num + OFFSET] = false;
            }
        }
        List<Integer> result2 = new ArrayList<>();
        for (int num : nums2) {
            if (set2[num + OFFSET] && !set1[num + OFFSET]) {
                result2.add(num);
                set2[num + OFFSET] = false;
            }
        }
        return List.of(result1, result2);
    }

    public static void main(String[] args) {
        FindTheDifferenceTwoArraysE obj = new FindTheDifferenceTwoArraysE();
        int[] num1 = new int[]{1, 2, 3};
        int[] num2 = new int[]{2, 4, 6};
        System.out.println(obj.findDifference(num1, num2));
    }
}
