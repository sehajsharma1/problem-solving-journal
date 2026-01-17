package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
Return intervals after the insertion.
Note that you don't need to modify intervals in-place. You can make a new array and return it.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
*/
public class InsertIntervalM {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        int[][] tempArr = new int[intervals.length + 1][];
        int idx = 0;
        boolean inserted = false;

        for (int[] interval : intervals) {
            if (!inserted && newInterval[0] < interval[0]) {
                tempArr[idx++] = newInterval;
                inserted = true;
            }
            tempArr[idx++] = interval;
        }

        if (!inserted) {
            tempArr[idx++] = newInterval;
        }
        int index = 0; // Tracks merged position
        for (int i = 1; i < tempArr.length; i++) {

            // If overlapping → merge
            if (tempArr[index][1] >= tempArr[i][0]) {
                tempArr[index][1] = Math.max(tempArr[index][1], tempArr[i][1]);
            } else {
                // Move forward if no overlap
                index++;
                tempArr[index] = tempArr[i];
            }
        }
        // Return only the merged portion
        return Arrays.copyOf(tempArr, index + 1);
    }

    public int[][] insertV1(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int n = intervals.length;
        int i = 0;
        while (i < n && intervals[i][1] < newInterval[0]) {
            merged.add(intervals[i]);
            i++;
        }
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        merged.add(newInterval);
        while (i < n) {
            merged.add(intervals[i]);
            i++;
        }
        int[][] result = new int[merged.size()][];
        for (int j = 0; j < merged.size(); j++) {
            result[j] = merged.get(j);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3}, {6, 9}
        };
        int[] intervalsV1 = {2, 5};
        InsertIntervalM obj = new InsertIntervalM();
        int[][] result = obj.insertV1(intervals, intervalsV1);
        System.out.println("Insert Intervals: " + Arrays.deepToString(result));

    }
}
