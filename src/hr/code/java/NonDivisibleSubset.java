package hr.code.java;

import java.util.List;

public class NonDivisibleSubset {

    public static void main(String[] args) {
        System.out.println(nonDivisibleSubset(4, List.of(19, 10, 12, 10, 24, 25, 22)));

    }

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        int maximalSubsetSize = 0;
        int[] countArray = new int[k];
        for (int i = 0; i < s.size(); i++) {
            int number = s.get(i);
            countArray[number % k]++;
        }
        for (int i = 0; i < countArray.length; i++) {
            int p = i;
            int q = k - p;
            if (q < p) {
                break;
            }

            if (p == 0 || p == q) {
                maximalSubsetSize = maximalSubsetSize + Math.min(countArray[p], 1);
            } else {
                maximalSubsetSize = maximalSubsetSize + Math.max(countArray[p], countArray[q]);
            }


        }


        return maximalSubsetSize;
    }
}
