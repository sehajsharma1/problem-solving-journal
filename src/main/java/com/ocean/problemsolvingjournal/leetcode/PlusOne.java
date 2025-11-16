package com.ocean.problemsolvingjournal.leetcode;
/*You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.*/
import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
        int[] digits = {1, 2, 3};
        int[] digitsV1 = {1, 2, 3};
        PlusOne plusOneObj = new PlusOne();
        int[] result = plusOneObj.plusOne(digits);
        int[] resultV1 = plusOneObj.plusOneV1(digitsV1);
        System.out.println(Arrays.toString(result) + " " + Arrays.toString(resultV1));
    }

    public int[] plusOne(int[] digits) {
        int length = digits.length;
        int all9 = 0;

        if (digits[length - 1] != 9) {
            digits[length - 1] = digits[length - 1] + 1;
            return digits;
        }

        for (int i = 0; i < length; i++) {
            if (digits[i] == 9) {
                all9 = 1;
            } else {
                all9 = 0;
                break;
            }
        }

        if (all9 == 1) {
            int[] arr = new int[length + 1];
            arr[0] = 1;
            return arr;
        } else {
            int carry = 1;
            for (int i = length - 1; i >= 0; i--) {
                if (digits[i] == 9 && carry == 1) {
                    digits[i] = 0;
                } else if (carry == 1) {
                    digits[i] = digits[i] + carry;
                    carry = 0;

                }
            }
            return digits;

        }
    }

    public int[] plusOneV1(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] results = new int[digits.length + 1];
        results[0] = 1;
        return results;

    }
}
