package com.ocean.problemsolvingjournal.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberOfArrowsToBurstBalloonsM {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));

        int index = 0;
        int count = 1;
        int i = 1;

        while (i < points.length) {
            if (points[index][1] >= points[i][0]) {
                i++;
            } else {
                count++;
                index = i;
                i++;
            }
        }
        return count;
    }

    public int findMinArrowShotsV1(int[][] points) {
        if (points == null || points.length == 0) return 0;

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int arrowPos = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > arrowPos) {
                arrows++;
                arrowPos = points[i][1];
            }
        }

        return arrows;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {10, 16},
                {2, 8},
                {1, 6},
                {7, 12}
        };
        MinimumNumberOfArrowsToBurstBalloonsM obj = new MinimumNumberOfArrowsToBurstBalloonsM();
        System.out.println(obj.findMinArrowShotsV1(intervals));
    }
}
