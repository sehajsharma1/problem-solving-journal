package com.ocean.problemsolvingjournal.hr;

public class BiggerIsGreater {
    public static void main(String[] args) {
        String word = "fedcbabcd";
        System.out.println(biggerIsGreater(word));
    }

    public static String biggerIsGreater(String w) {
        char[] charArr = w.toCharArray();
        int length = charArr.length;
        char tempChar = 0;
        int charLocation = 0;
        for (int i = length - 1; i >= 1; i--) {
            if (charArr[i - 1] < charArr[i]) {
                charLocation = i - 1;
                tempChar = charArr[charLocation];
                for (int j = length - 1; j > charLocation; j--) {
                    if (charArr[j] > tempChar) {
                        charArr[charLocation] = charArr[j];
                        charArr[j] = tempChar;
                        break;
                    }
                }

            }
            if (tempChar != 0) {
                reverse(charArr, charLocation + 1, length - 1);
                return String.valueOf(charArr);
            }
        }

        return "no answer";
    }

    private static void reverse(char[] ar, int i, int j) {
        if (i > j) return;
        else {
            char temp = ar[i];
            ar[i] = ar[j];
            ar[j] = temp;
            reverse(ar, ++i, --j);
        }
    }
}
