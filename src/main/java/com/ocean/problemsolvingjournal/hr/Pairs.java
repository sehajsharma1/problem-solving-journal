package com.ocean.problemsolvingjournal.hr;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*Given an array of integers and a target value, determine the number of pairs of array elements that have a difference equal to the target value.*/
public class Pairs {
    public static void main(String[] args) {
        System.out.println(pairs(1, Arrays.asList(1, 2, 3, 4)));

    }

    public static int pairs(int k, List<Integer> arr) {

        int count = 0;
        Collections.sort(arr);
        for (Integer num : arr) {
            if (indexedBinarySearch(arr, num + k) >= 0) {
                count++;
            }
        }
        return count;
    }

    private static <T> int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = list.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }
}
