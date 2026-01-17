package com.ocean.problemsolvingjournal.leetcode;
/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]
*/
class WordDictionaryM {

    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }

    private final TrieNode root = new TrieNode();

    // Add word: O(L)
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (node.child[idx] == null) {
                node.child[idx] = new TrieNode();
            }
            node = node.child[idx];
        }
        node.isWord = true;
    }

    // Search with wildcard
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    // DFS only when '.' appears
    private boolean dfs(String word, int i, TrieNode node) {
        if (node == null) return false;
        if (i == word.length()) return node.isWord;

        char c = word.charAt(i);

        if (c == '.') {
            for (int k = 0; k < 26; k++) {
                if (node.child[k] != null && dfs(word, i + 1, node.child[k])) {
                    return true;
                }
            }
            return false;
        }

        return dfs(word, i + 1, node.child[c - 'a']);
    }

    public static void main(String[] args) {
        WordDictionaryM dict = new WordDictionaryM();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");

        System.out.println("search(\"pad\"): " + dict.search("pad")); // false
        System.out.println("search(\"bad\"): " + dict.search("bad")); // true
        System.out.println("search(\".ad\"): " + dict.search(".ad")); // true
        System.out.println("search(\"b..\"): " + dict.search("b..")); // true
    }
}
