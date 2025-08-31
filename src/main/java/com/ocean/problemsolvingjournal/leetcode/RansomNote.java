package leetcode.code.java;

import java.util.HashMap;

/*
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.



Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false
Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false
Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true
 */
class RansomNote {
    public static void main(String[] args) {
        RansomNote solution = new RansomNote();
        String ransomNote = "sehajs";
        String magazine = "sajbseha";
        System.out.println(solution.canConstruct(ransomNote, magazine));
        System.out.println(solution.canConstructV1(ransomNote, magazine));// Output: false
    }

    public boolean canConstruct(String ransomNote, String magazine) {

        char[] ransomNoteChars = ransomNote.toCharArray();
        char[] magazineChars = magazine.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNoteChars.length; i++) {
            map.put(ransomNoteChars[i], map.getOrDefault(ransomNoteChars[i], 0) + 1);
        }
        for (int i = 0; i < magazineChars.length; i++) {
            if (map.containsKey(magazineChars[i])) {
                map.put(magazineChars[i], map.get(magazineChars[i]) - 1);
                if (map.get(magazineChars[i]) == 0) {
                    map.remove(magazineChars[i]);
                }
            }
        }
        if (map.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canConstructV1(String ransomNote, String magazine) {
        int[] alphabet = new int[26];
        for (char c : ransomNote.toCharArray()) {
            int i = magazine.indexOf(c, alphabet[c % 26]);
            if (i == -1) return false;
            alphabet[c % 26] = i + 1;
        }
        return true;
    }
}
