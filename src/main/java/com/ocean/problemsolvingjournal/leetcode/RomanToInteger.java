package com.ocean.problemsolvingjournal.leetcode;

/*Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
*/
public class RomanToInteger {
    public static void main(String[] args) {
        String s = "ICM";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt2(s));
    }

    public static int romanToInt(String s) {
        char[] arr = s.toCharArray();
        int total = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            char romanChar = arr[i];
            char romanCharPrev = i == 0 ? arr[i] : arr[i - 1];
            switch (romanChar) {
                case 'I':
                    total = total + 1;
                    break;
                case 'V':
                    if (romanCharPrev == 'I') {
                        total = total + 4;
                        --i;

                    } else {
                        total = total + 5;
                    }
                    break;
                case 'X':
                    if (romanCharPrev == 'I') {
                        total = total + 9;
                        --i;

                    } else {
                        total = total + 10;
                    }
                    break;
                case 'L':
                    if (romanCharPrev == 'X') {
                        total = total + 40;
                        --i;

                    } else {
                        total = total + 50;
                    }
                    break;
                case 'C':
                    if (romanCharPrev == 'X') {
                        total = total + 90;
                        --i;

                    } else {
                        total = total + 100;
                    }
                    break;
                case 'D':
                    if (romanCharPrev == 'C') {
                        total = total + 400;
                        --i;

                    } else {
                        total = total + 500;
                    }
                    break;
                case 'M':
                    if (romanCharPrev == 'C') {
                        total = total + 900;
                        --i;

                    } else {
                        total = total + 1000;
                    }
                    break;

            }
        }
        return total;

    }

    private static int translate(char c) {
        switch (c) {
            case 'I':
                return 1;

            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static int romanToInt2(String s) {
        int sum = 0;
        int current = translate(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int next = translate(s.charAt(i));
            if (current < next)
                sum -= current;
            else
                sum += current;
            current = next;
        }
        sum += current;
        return sum;
    }
}
