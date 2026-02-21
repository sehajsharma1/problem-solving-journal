package com.ocean.problemsolvingjournal.hr;

import java.util.*;

/*Christy wants to give chocolates to her colleagues, and at the same time tries to ensure that everyone has equal chocolates at the end. To achieve this she either gives 1,2, or 5 chocolates to everyone except any one individual.*/

public class Equal {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 5, 5);
        System.out.println(equal(arr));
    }

    public static int equal(List<Integer> arr) {
        // Store all the possibilities
        int[] possibilities = new int[5];
        // Start with the minimum element
        int minimum = Collections.min(arr);
        for (int i = 0; i < possibilities.length; i++) {
            for (int element : arr) {
                int diff = element - minimum;
                int stepsRequired = diff / 5 + (diff % 5) / 2 + ((diff % 5) % 2) / 1;
                possibilities[i] += stepsRequired;
            }
            minimum--;
        }
        // Return the minimum number out of all the possibilities
        return Arrays.stream(possibilities).min().getAsInt();
    }
}
