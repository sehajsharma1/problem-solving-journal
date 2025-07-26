package hr.code.java;

import java.util.ArrayList;
import java.util.List;

public class FormingAMagicSquare {
    public static void main(String[] args)
    {
        List<List<Integer>> list = new ArrayList<>();
        list.add(List.of(5,3,4));
        list.add(List.of(1,5,8));
        list.add(List.of(6,4,2));
       System.out.println(formingMagicSquare(list));

    }

    private static int formingMagicSquare(List<List<Integer>> s) {
        int[][][] possible = new int[][][]{
                {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
                {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
                {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
                {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
                {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
                {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
                {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
                {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}}
        };
        int minCost = 45;
        for (int[][] ints : possible) {
            int varCost = 0;
            for (int j = 0; j < ints.length; j++) {
                for (int k = 0; k < ints[j].length; k++) {
                    varCost += Math.abs(ints[j][k] - s.get(j).get(k));
                }
            }
            if (varCost < minCost) minCost = varCost;
        }


        return minCost;
    }

}
