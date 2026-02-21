package com.ocean.problemsolvingjournal.hr;

import java.io.IOException;
import java.util.*;

/*Sherlock considers a string to be valid if all characters of the string appear the same number of times. It is also valid if he can remove just character at index in the string, and the remaining characters will occur the same number of times. Given a string , determine if it is valid. If so, return YES, otherwise return NO.*/

public class SherlockAndValidString {
    public static void main(String[] args) throws IOException {
        String str = "aaaabbcc";
        System.out.println(isValid(str));
    }

    public static String isValid(String s) {
        char[] charArr = s.toCharArray();
        int count = 0;
        char character = '0';
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < charArr.length; i++) {
            Character key = charArr[i];
            if (hashMap.containsKey(key)) {
                character = key;
                --count;
                int value = hashMap.get(key);
                hashMap.put(key, value + 1);
            } else {
                count++;
                hashMap.put(key, 1);
            }
        }
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        List<Integer> mapValues = new ArrayList<>();
        for (Integer val : hashMap.values()) {
            if (val < minValue) {
                minValue = val;
            }
            if (val > maxValue) {
                maxValue = val;
            }
            mapValues.add(val);
        }
        int frequency = hashMap.keySet().size();
        String result = "NO";
        if (count == frequency - 1 && hashMap.get(character).intValue() == 2) {
            result = "YES";
        } else if (minValue == maxValue) {
            result = "YES";
        } else {
            if (minValue == 1) {
                count = countFrequencyOfValue(maxValue, mapValues);

            } else {
                if ((minValue + 1) == maxValue) {
                    count = countFrequencyOfValue(minValue, mapValues);
                }
            }
            if (count != 0 && count == frequency - 1) {
                result = "YES";
            }

        }
        return result;
    }

    private static int countFrequencyOfValue(int valueToCompare, List<Integer> list) {
        int count = 0;
        for (int val : list) {
            if (val == valueToCompare) {
                ++count;
            }
        }
        return count;
    }
}
