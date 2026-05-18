package com.ocean.problemsolvingjournal.leetcode.seventyfive.prefix_sum;

/*
There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.

You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.

Example 1:

Input: gain = [-5,1,5,0,-7]
Output: 1
Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
*/
public class FindHighestAltitudeE {
    public int largestAltitude(int[] gain) {
        int highest = 0;
        int sum = 0;
        for (int num : gain) {
            sum = sum + num;

            highest = Math.max(highest, sum);
        }
        return highest;
    }

    public static void main(String[] args) {
        FindHighestAltitudeE sol = new FindHighestAltitudeE();
        int[] arr = new int[]{-5, 1, 5, 0, -7};
        System.out.println(sol.largestAltitude(arr));
    }
}
