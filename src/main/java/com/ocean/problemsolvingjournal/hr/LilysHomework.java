package com.ocean.problemsolvingjournal.hr;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/*Whenever George asks Lily to hang out, she’s busy doing homework. George wants to help her finish it faster, but he’s in over his head! Can you help George understand Lily’s homework so she can hang out with him?

Consider an array of n distinct integers, arr= [a[0], a[1],…,a[n-1]]. George can swap any two elements of the array any number of times. An array is beautiful if the sum of |a[i] — a[i-1]| among 0 < i < n is minimal.

Given the array arr, determine and return the minimum number of swaps that should be performed in order to make the array beautiful.
*/

public class LilysHomework {

    public static void main(String[] args) {
        System.out.println(lilysHomework(List.of(101, 758, 315, 730, 472, 619, 460, 479)));
    }

    public static int lilysHomework(List<Integer> arr) {
        List<Integer> sortedArr = arr.stream().sorted().collect(Collectors.toList());
        List<Integer> sortedArrDesc = arr.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        HashMap<Integer, Integer> arrMap = new HashMap<>();
        int asc = 0, desc = 0;
        for (int m = 0; m < arr.size(); m++) {
            arrMap.put(arr.get(m).intValue(), m);
        }
        while (true) {
            int i = 0;
            for (; i < arr.size(); i++) {
                if (sortedArr.get(i).intValue() != arr.get(i).intValue()) {
                    int location = arrMap.get(sortedArr.get(i).intValue());
                    int temp = sortedArr.get(location).intValue();
                    sortedArr.set(location, sortedArr.get(i).intValue());
                    sortedArr.set(i, temp);
                    asc++;
                }
                if (sortedArrDesc.get(i).intValue() != arr.get(i).intValue()) {
                    int location = arrMap.get(sortedArrDesc.get(i).intValue());
                    int temp = sortedArrDesc.get(location).intValue();
                    sortedArrDesc.set(location, sortedArrDesc.get(i).intValue());
                    sortedArrDesc.set(i, temp);
                    desc++;
                }
            }
            int countAsc = 0;
            int countDesc = 0;
            if (asc <= desc) {
                for (int j = 0; j < arr.size(); j++) {
                    if (sortedArr.get(j).intValue() == arr.get(j).intValue()) {
                        ++countAsc;
                    }
                }
            } else {
                for (int j = 0; j < arr.size(); j++) {
                    if (sortedArrDesc.get(j).intValue() == arr.get(j).intValue()) {
                        ++countDesc;
                    }
                }
            }

            if (countAsc == arr.size() || countDesc == arr.size()) {
                break;
            }


        }
        if (asc <= desc)
            return asc;
        else
            return desc;
    }
}
