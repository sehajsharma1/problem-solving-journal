package leetcode.code.java;
/* Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.*/

public class Hindex {
    public static void main(String[] args) {
        int[] citations = new int[]{3, 0, 6, 1, 5};
        System.out.println(hIndex(citations));
    }
    public static int hIndex(int[] citations) {
        int lo = 0;
        int hi = citations.length;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean isItPossible = checkIfPossible(citations, mid);

            if (isItPossible) {
                ans = mid;
                // try to optimize the answer
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
    public static boolean checkIfPossible(int[] arr, int val) {

        // count the number of citations having value more than val
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= val)
                count++;
        }
        return (count >= val) ? true : false;
    }
}
