package com.ocean.problemsolvingjournal.hr;

import java.util.ArrayList;
import java.util.List;

/*
You are given a Max Heap containing m integers, represented as an array heap, where the heap follows the max-heap property.

You are also given:
An integer k
An integer n

Your task is to find the Kth largest element in the max heap and compare it with n.

Return:

"EQUAL" if n is equal to the Kth largest element
"GREATER" if n is greater than the Kth largest element
"LESS" if n is less than the Kth largest element
*/
public class CompareWithKthLargestM {
    public String compareWithKthLargest(int[] heap, int k, int n) {

        int kthLargest = findKthLargest(heap, k);
        if (n == kthLargest) {
            return "EQUAL";
        } else if (n > kthLargest) {
            return "GREATER";
        } else {
            return "LESS";
        }
    }

    public int findKthLargest(int[] nums, int k) {
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
        CompareWithKthLargestM sol = new CompareWithKthLargestM();
        int[] heap = {100, 50, 90, 20, 40, 80, 85};
        int k = 3;
        int n = 85;

        String result = sol.compareWithKthLargest(heap, k, n);
        System.out.println(result);
    }
}
