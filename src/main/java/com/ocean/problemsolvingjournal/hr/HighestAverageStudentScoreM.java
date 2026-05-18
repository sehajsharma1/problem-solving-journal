package com.ocean.problemsolvingjournal.hr;

import java.util.HashMap;
import java.util.Map;

/*
You are given a 2D string array scores, where each scores[i] = [studentName, mark] represents the name of a student and one of their marks.

A student may appear multiple times in the array.

Return the highest average score among all students.

The average score of a student is the sum of all their marks divided by the number of marks.
If the average is not an integer, round it down to the nearest integer.
Marks may be negative.
Example 1:
Input: scores = [
 ["Bob","87"],
 ["Mike","35"],
 ["Bob","52"],
 ["Jason","35"],
 ["Mike","55"],
 ["Jessica","99"]
]

Output: 99
*/
public class HighestAverageStudentScoreM {

    public int highestAverage(String[][] scores) {

        Map<String, int[]> map = new HashMap<>();

        for (String[] entry : scores) {

            String name = entry[0];
            int mark = Integer.parseInt(entry[1]);

            map.putIfAbsent(name, new int[2]);

            map.get(name)[0] += mark;   // sum
            map.get(name)[1] += 1;      // count
        }

        int maxAverage = Integer.MIN_VALUE;

        for (int[] value : map.values()) {

            int sum = value[0];
            int count = value[1];

            int avg = (int) Math.floor((double) sum / count);

            maxAverage = Math.max(maxAverage, avg);
        }

        return maxAverage;
    }


public static void main(String[] args) {

    HighestAverageStudentScoreM sol = new HighestAverageStudentScoreM();

    String[][] scores = {
            {"Bob", "87"},
            {"Mike", "35"},
            {"Bob", "52"},
            {"Jason", "35"},
            {"Mike", "55"},
            {"Jessica", "99"}
    };

    int result = sol.highestAverage(scores);

    System.out.println("Highest Average Score: " + result);
}
}
