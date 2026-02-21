package com.ocean.problemsolvingjournal.hr;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MiniMaxSum {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(793810624, 895642170, 685903712, 623789054, 468592370);
        long sum = 0;
        int minimumNumber = arr.get(0);
        int maxNumber = arr.get(0);

        int arrSize = arr.size();
        for (int i = 0; i < arrSize; i++) {
            int value = arr.get(i);
            if (minimumNumber > value) {
                minimumNumber = value;
            }
            if (maxNumber < value) {
                maxNumber = value;
            }
            sum = sum + Long.valueOf(value);
        }
        System.out.println((sum-maxNumber) + " " + (sum-minimumNumber));

    }
}
