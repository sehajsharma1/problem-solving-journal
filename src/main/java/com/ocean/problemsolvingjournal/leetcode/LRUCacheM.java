package com.ocean.problemsolvingjournal.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]
*/
public class LRUCacheM {

    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node head, tail; // dummy nodes

    public LRUCacheM(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // create dummy head & tail
        head = new Node(200, 200);
        tail = new Node(-100, -100);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);

        remove(node);
        insertToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;

            remove(node);
            insertToHead(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;   // least recently used
                remove(lru);
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            insertToHead(newNode);
            map.put(key, newNode);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToHead(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {

        LRUCacheM cache = new LRUCacheM(2);

        cache.put(1, 1);
        System.out.println("put(1,1)");

        cache.put(2, 2);
        System.out.println("put(2,2)");

        System.out.println("get(1): " + cache.get(1)); // returns 1

        cache.put(3, 3); // evicts key 2
        System.out.println("put(3,3)");

        System.out.println("get(2): " + cache.get(2)); // returns -1

        cache.put(4, 4); // evicts key 1
        System.out.println("put(4,4)");

        System.out.println("get(1): " + cache.get(1)); // returns -1
        System.out.println("get(3): " + cache.get(3)); // returns 3
        System.out.println("get(4): " + cache.get(4)); // returns 4
    }
}
