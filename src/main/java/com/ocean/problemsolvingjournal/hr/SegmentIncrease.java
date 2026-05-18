package com.ocean.problemsolvingjournal.hr;

import java.util.Arrays;

/*
Choose a continuous segment and add value x
so the whole array becomes strictly increasing.

Example:
[4,2,4,1,3,5]
 */
public class SegmentIncrease {

    public static int makeIncreasing(int[] arr) {

        int n = arr.length;

        if (n <= 1) {
            return 0;
        }

        int operations = 0;

        int i = 0;

        while (i < n - 1) {

            // find current increasing segment
            int segEnd = i;

            while (segEnd + 1 < n &&
                    arr[segEnd + 1] > arr[segEnd]) {
                segEnd++;
            }

            // no next segment
            if (segEnd == n - 1) {
                break;
            }

            int nextStart = segEnd + 1;

            // find next increasing segment
            int nextEnd = nextStart;

            while (nextEnd + 1 < n &&
                    arr[nextEnd + 1] > arr[nextEnd]) {
                nextEnd++;
            }

            /*
             current segment last value
             must be smaller than next segment first value
            */
            if (arr[nextStart] <= arr[segEnd]) {

                int add = arr[segEnd] - arr[nextStart] + 1;

                // add value to entire next segment
                for (int k = nextStart; k <= nextEnd; k++) {
                    arr[k] += add;
                }

                operations++;
            }

            // move to next segment
            i = nextStart;
        }

        return operations;
    }

    public static void main(String[] args) {

        int[] arr = {4, 2, 4, 1, 3, 5};

        int operations = makeIncreasing(arr);

        System.out.println("Operations = " + operations);

        System.out.println("Final Array = " + Arrays.toString(arr));
    }
}
