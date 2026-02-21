package com.ocean.problemsolvingjournal.leetcode;

import java.util.*;

/*
A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
Note that the starting point is assumed to be valid, so it might not be included in the bank.

Example 1:

Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1
*/

public class MinimumGeneticMutationM {

    public int minMutation(String start, String end, String[] bank) {

        Set<String> set = new HashSet<>(Arrays.asList(bank));

        if (!set.contains(end)) return -1;

        char[] genes = {'A', 'C', 'G', 'T'};

        Queue<String> q = new LinkedList<>();
        q.offer(start);

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                String curr = q.poll();

                if (curr.equals(end)) return steps;

                char[] arr = curr.toCharArray();

                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];

                    for (char c : genes) {
                        arr[i] = c;
                        String next = new String(arr);

                        if (set.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            q.offer(next);
                        }
                    }

                    arr[i] = old;
                }
            }

            steps++;
        }

        return -1;
    }


    public static void main(String[] args) {
        MinimumGeneticMutationM solver = new MinimumGeneticMutationM();


        // Example 1
        String start2 = "AACCGGTT";
        String end2 = "AAACGGTA";
        String[] bank2 = new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        int res2 = solver.minMutation(start2, end2, bank2);
        System.out.println("Example 2 result: " + res2 + " (expected: 2)");

        // Example 2 (no possible mutation)
        String start3 = "AAAAACCC";
        String end3 = "AACCCCCC";
        String[] bank3 = new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        int res3 = solver.minMutation(start3, end3, bank3);
        System.out.println("Example 3 result: " + res3 + " (expected: 3)");
    }

}
