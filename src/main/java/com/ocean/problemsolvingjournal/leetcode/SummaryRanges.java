package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.List;

/*You are given a sorted unique integer array nums.

A range [a,b] is the set of all integers from a to b (inclusive).

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b*/
public class SummaryRanges {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 2, 3, 4, 6, 8, 10};
        SummaryRanges summaryRanges = new SummaryRanges();
        System.out.println(summaryRanges.summaryRanges(nums));

    }

    public List<String> summaryRanges(int[] nums) {

        int numsLength = nums.length;
        List<String> list = new ArrayList<>();
        int range = 0;
        int count = 0;
        for (int i = 0; i < numsLength; i++) {
            range = nums[i] + 1;
            if (i + 1 < numsLength && range == nums[i + 1]) {
                ++count;
            } else {
                StringBuilder sb = new StringBuilder();
                if (count > 0) {
                    range = range - 1;
                    sb.append(String.valueOf(range - count)).append("->").append(String.valueOf(range));
                    list.add(sb.toString());
                    count = 0;
                } else {
                    sb.append(String.valueOf(nums[i]));
                    list.add(sb.toString());
                    count = 0;
                }
            }
        }
        return list;
    }
}
