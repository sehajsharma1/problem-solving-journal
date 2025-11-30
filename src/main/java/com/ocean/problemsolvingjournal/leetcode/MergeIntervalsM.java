package com.ocean.problemsolvingjournal.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
*/
public class MergeIntervalsM {

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {15, 18},
                {2, 6},
                {8, 10}

        };
        int[][] intervalsV1 = {
                {0, 0},
                {1, 4}

        };

        MergeIntervalsM obj = new MergeIntervalsM();
        int[][] result = obj.merge(intervals);
        int[][] resultV1 = obj.mergeV1(intervalsV1);
        System.out.println("Merged Intervals: " + Arrays.deepToString(result));
        System.out.println("Merged Intervals: " + Arrays.deepToString(resultV1));
    }

    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        // Step 1: Sort by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int index = 0; // Tracks merged position
        for (int i = 1; i < intervals.length; i++) {

            // If overlapping → merge
            if (intervals[index][1] >= intervals[i][0]) {
                intervals[index][1] = Math.max(intervals[index][1], intervals[i][1]);
            } else {
                // Move forward if no overlap
                index++;
                intervals[index] = intervals[i];
            }
        }
        // Return only the merged portion
        return Arrays.copyOf(intervals, index + 1);
    }

    public int[][] mergeV1(int[][] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) max = Math.max(max, arr[i][0]);
        int[] prefix = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            int s = arr[i][0];
            int e = arr[i][1];
            prefix[s] = Math.max(prefix[s], e + 1);
        }
        int r = 0;
        int s = -1;
        int e = -1;
        for (int i = 0; i < prefix.length; i++) {
            if (prefix[i] != 0) {
                if (s == -1) s = i;
                e = Math.max(e, prefix[i] - 1);
            }
            if (i == e) {
                arr[r++] = new int[]{s, e};
                s = -1;
                e = -1;
            }
        }
        if (s != -1) arr[r++] = new int[]{s, e};
        if (r == arr.length) return arr;
        int[][] result = new int[r][];
        for (int i = 0; i < r; i++) result[i] = arr[i];
        return result;
    }

}

