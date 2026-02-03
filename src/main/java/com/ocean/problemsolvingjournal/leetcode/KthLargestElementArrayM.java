package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
Can you solve it without sorting?

Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
*/
public class KthLargestElementArrayM {

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap

        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll(); // remove smallest
            }
        }
        return pq.peek();
    }

    public int findKthLargestV1(int[] nums, int k) {

        int n = nums.length;

        // kth largest -> convert to kth smallest index
        int target = n - k;

        int left = 0, right = n - 1;

        while (true) {

            int pivotIndex = partition(nums, left, right);

            if (pivotIndex == target)
                return nums[pivotIndex];

            else if (pivotIndex < target)
                left = pivotIndex + 1;

            else
                right = pivotIndex - 1;
        }
    }

    // partition like quicksort
    private int partition(int[] arr, int left, int right) {

        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int findKthLargestV2(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(n, min);
            max = Math.max(n, max);
        }
        int len = max - min + 1;
        List<Integer>[] buckets = new List[len];
        for (int n : nums) {
            int index = Math.abs(n - min);
            if (buckets[index] == null) {
                buckets[index] = new ArrayList<>();
            }
            buckets[index].add(buckets[index].size() + 1);
        }
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                k -= buckets[i].size();
                if (k <= 0) {
                    return i + min;
                }
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        KthLargestElementArrayM solver = new KthLargestElementArrayM();
        // Default/example run
        int[] nums = {5, 6, 3, 3, 3, 2, 1, 4};
        int k = 2;
        System.out.println("Example: nums = " + java.util.Arrays.toString(nums) + ", k = " + k
                + " => " + solver.findKthLargestV1(nums, k));
    }
}
