package com.ocean.problemsolvingjournal.leetcode;

/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
Implement the Trie class:
Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:
Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]
*/
public class PrefixTreeM {

    TrieNode root;

    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord = false;
    }

    public PrefixTreeM() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.child[c - 'a'] == null) {
                node.child[c - 'a'] = new TrieNode();
            }
            node = node.child[c - 'a'];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.child[c - 'a'] == null) {
                return false;
            }
            node = node.child[c - 'a'];
        }
        return node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.child[c - 'a'] == null) {
                return false;
            }
            node = node.child[c - 'a'];
        }
        return true;
    }


    public static void main(String[] args) {
        PrefixTreeM trie = new PrefixTreeM();
        trie.insert("apple");
        System.out.println("search(\"apple\"): " + trie.search("apple")); // true
        System.out.println("search(\"app\"): " + trie.search("app"));     // false
        System.out.println("startsWith(\"app\"): " + trie.startsWith("app")); // true
        trie.insert("map");
        System.out.println("search(\"map\"): " + trie.search("map"));     // true
    }
}
