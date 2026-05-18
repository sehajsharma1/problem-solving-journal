package com.ocean.problemsolvingjournal.leetcode;

import java.util.*;

/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.



Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
*/
public class WordLadderH {

    public static void main(String[] args) {

        WordLadderH obj = new WordLadderH();

        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = Arrays.asList(
                "hot", "dot", "dog", "lot", "log", "cog"
        );

        int result = obj.ladderLengthV1(beginWord, endWord, wordList);

        System.out.println("Shortest transformation length: " + result);
    }
    /*
    Used wildcard pattern mapping (* replacement) with HashMap to efficiently find neighboring words differing by one character.
    Applied BFS using a queue to compute the shortest transformation sequence in an unweighted graph.
*/

    public int ladderLengthV1(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) return 0;

        // Step 1: Build pattern map
        Map<String, List<String>> map = new HashMap<>();

        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);

                map.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        // Step 2: BFS
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                if (word.equals(endWord)) return level;

                for (int j = 0; j < word.length(); j++) {
                    String pattern = word.substring(0, j) + "*" + word.substring(j + 1);

                    List<String> neighbors = map.getOrDefault(pattern, new ArrayList<>());

                    for (String next : neighbors) {
                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }

            level++;
        }

        return 0;
    }

    /*- Implemented Bidirectional BFS to simultaneously search from start and end words, reducing search space significantly.
- Generated transformations dynamically to achieve near O(N × L) time complexity with optimized performance.*/

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        // Bidirectional BFS sets
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        Set<String> visited = new HashSet<>();
        int level = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {

            // Always expand smaller set (optimization)
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextLevel = new HashSet<>();

            for (String word : beginSet) {
                char[] arr = word.toCharArray();

                for (int i = 0; i < arr.length; i++) {
                    char original = arr[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[i] = c;
                        String newWord = new String(arr);

                        // Found connection
                        if (endSet.contains(newWord)) {
                            return level + 1;
                        }

                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            visited.add(newWord);
                            nextLevel.add(newWord);
                        }
                    }

                    arr[i] = original; // restore
                }
            }

            beginSet = nextLevel;
            level++;
        }

        return 0;
    }

}
