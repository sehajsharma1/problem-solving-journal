package com.ocean.problemsolvingjournal.hr;

import java.util.List;

/*Larry has been given a permutation of a sequence of natural numbers incrementing from 1, as an array.
He must determine if the array can be sorted using the following operation any number of times (Choose 3 indexes):
ABC → BCA → CAB → ABC
*/
public class LarrysArray {
    public static void main(String[] args) {
        System.out.println(larrysArray(List.of(1, 6, 5, 2, 4, 3)));
    }


    /*here what I am doing I am counting total number of inversion pairs .
    If they are Even then it is possible to get sorted array after rotating triples.
    since after rotating or transposing the array the total number of inversions will either increase by 2 or decrease by 2 so if total number of inversions are even then eventually after rotating after some point you'll get inversions 0 which implies that array is sorted but in case you have total inversions odd you can never have 0 inversions after rotating or transposing*/
    public static String larrysArray(List<Integer> A) {
        int count = 0;
        for (int i = 0; i < A.size(); ++i) {
            for (int j = i + 1; j < A.size(); ++j) {
                if (A.get(i) > A.get(j)) {
                    count++;
                }
            }
        }

        return (count % 2 == 0) ? "YES" : "NO";
    }
}
