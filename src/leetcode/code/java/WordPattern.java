package leetcode.code.java;

import java.util.*;


/*Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:

Each letter in pattern maps to exactly one unique word in s.
Each unique word in s maps to exactly one letter in pattern.
No two letters map to the same word, and no two words map to the same letter.


Example 1:

Input: pattern = "abba", s = "dog cat cat dog"

Output: true

Explanation:

The bijection can be established as:

'a' maps to "dog".
'b' maps to "cat".*/
public class WordPattern {
    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        WordPattern solution = new WordPattern();
        boolean result = solution.wordPattern(pattern, s);
        System.out.println(result);
        System.out.println(solution.wordPatternV1(pattern, s));

    }

    public boolean wordPattern(String pattern, String s) {
        char[] charP = pattern.toCharArray();
        String[] arrS = s.split(" ");
        String[] sMap = new String[128];
        HashMap<String, Integer> map = new HashMap<>();
        if (charP.length != arrS.length)
            return false;
        for (int i = 0; i < charP.length; i++) {
            int tempP = charP[i];
            String tempS = arrS[i];
            if (Objects.nonNull(sMap[tempP]) && !sMap[tempP].equals(tempS)) {
                return false;

            } else if (Objects.nonNull(sMap[tempP]) && sMap[tempP].equals(tempS)) {
                continue;

            } else if (Objects.isNull(sMap[tempP]) && !map.containsKey(tempS)) {
                sMap[tempP] = tempS;
                map.put(tempS, 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean wordPatternV1(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) return false;

        Map<Character, String> map = new HashMap<>();
        Set<String> usedWords = new HashSet<>();

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];

            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(word)) return false;
            } else {
                if (usedWords.contains(word)) return false;

                map.put(ch, word);
                usedWords.add(word);
            }
        }

        return true;
    }
}
