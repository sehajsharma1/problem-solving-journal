package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

/*
You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
Define a pair (u, v) which consists of one element from the first array and one element from the second array.
Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.

Example 1:
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
*/
public class FindKPairsWithSmallestSumsM {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();

        if (nums1.length == 0 || nums2.length == 0 || k == 0)
            return result;

        // Min heap based on sum (simplified comparator)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> nums1[a[0]] + nums2[a[1]])
        );

        // push first column
        for (int i = 0; i < nums1.length && i < k; i++) {
            pq.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[0], j = cur[1];

            result.add(Arrays.asList(nums1[i], nums2[j]));

            // push next column
            if (j + 1 < nums2.length) {
                pq.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }

    // Added main method to demonstrate usage
    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        FindKPairsWithSmallestSumsM solver = new FindKPairsWithSmallestSumsM();
        List<List<Integer>> result = solver.kSmallestPairs(nums1, nums2, k);

        System.out.println("k smallest pairs: " + result);
    }

}
