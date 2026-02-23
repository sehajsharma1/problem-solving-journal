package com.ocean.problemsolvingjournal.leetcode.seventyfive.array_string;

import java.util.ArrayList;
import java.util.List;

/*
There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
Note that multiple kids can have the greatest number of candies.
Input: candies = [4,2,1,1,2], extraCandies = 1
Output: [true,false,false,false,false]
*/
public class KidsWithTheGreatestNumberOfCandiesE {

    public static void main(String[] args) {
        KidsWithTheGreatestNumberOfCandiesE solution = new KidsWithTheGreatestNumberOfCandiesE();
        int[] candies = new int[]{4, 2, 1, 1, 2};
        int extraCandy = 1;
        solution.kidsWithCandies(candies, extraCandy).forEach(System.out::println);
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> list = new ArrayList<>(candies.length);
        int max = 0;
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] > max) {
                max = candies[i];
            }
        }
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                list.add(true);
            } else {
                list.add(false);
            }
        }
        return list;

    }

}
