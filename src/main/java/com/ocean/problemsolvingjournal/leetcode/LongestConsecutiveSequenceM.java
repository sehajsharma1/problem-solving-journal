package com.ocean.problemsolvingjournal.leetcode;

import java.util.HashSet;

/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.
Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
*/
public class LongestConsecutiveSequenceM {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num : set) {
            // Only start counting if this number is the start of a sequence
            if (!set.contains(num - 1)) {
                int current = num;
                int count = 1;
                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }

    public int longestConsecutiveV1(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        int longest = 0;

        // Find min and max in the array
        for (int a : arr) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }

        // If the range is not too big, use a boolean array
        if (max - min <= 100000)  {
            boolean[] b = new boolean[max - min + 1];
            for (int i : arr) {
                b[i - min] = true;
            }

            for (boolean val : b) {
                if (val) {
                    cnt++;
                } else {
                    longest = Math.max(cnt, longest);
                    cnt = 0;
                }
            }
            longest = Math.max(cnt, longest);

        } else {
            // Otherwise, use a HashSet approach
            HashSet<Integer> ele = new HashSet<>();
            for (int i : arr) {
                ele.add(i);
            }

            for (int i : ele) {
                if (!ele.contains(i - 1)) { // start of a sequence
                    int temp = i;
                    cnt = 0;
                    while (ele.contains(temp)) {
                        cnt++;
                        temp++;
                    }
                    longest = Math.max(longest, cnt);
                }
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequenceM sol = new LongestConsecutiveSequenceM();
        int[] input = {100, 4, 200, 1, 3, 2};
        System.out.println(sol.longestConsecutive(input));
        System.out.println(sol.longestConsecutiveV1(input));
    }
}
