package hr.code.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AlmostSorted {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(3,1,2);
        almostSorted(integerList);
    }

    public static void almostSorted(List<Integer> arr) {
        int i = 0, j = 0; // for range
        int count = 0; // to check swap or reverse
        int reverseCount = 1; // to check  reverse count
        int item = 0;
        List<Integer> integerList = arr.stream().sorted().collect(Collectors.toList());
        List<Integer> reverseList = new ArrayList<>();
        for (int z = 0; z < arr.size(); z++) {
            int number = arr.get(z);
            int sortedNumber = integerList.get(z);
            if (number == sortedNumber) {
                continue;
            } else {
                ++count;
                if (count == 1) {
                    i = z;
                    item = number;
                } else {
                    if (number < item) {
                        item = number;
                        ++reverseCount;
                    }
                    j = z;
                }
            }
        }
        i = i + 1;
        j = j + 1;
        if (count == 2) {
            System.out.println("yes");
            System.out.println("swap " + i + " " + j);

        } else if (count > 2) {
            if (count == reverseCount) {
                System.out.println("yes");
                System.out.println("reverse " + i + " " + j);
            } else {
                System.out.println("no");
            }
        } else {
            System.out.println("no");
        }


    }
}
