package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class RandomizedSet {
    public static void main(String[] args) {

        RandomizedSet obj = new RandomizedSet();
        obj.insert(1);
        obj.remove(2);
        obj.insert(2);
        obj.insert(3);
        obj.insert(4);
        obj.getRandom();
        obj.remove(1);
        obj.insert(2);
        obj.getRandom();
        obj.remove(3);
    }

    ArrayList<Integer> numsList;
    HashMap<Integer, Integer> locsMap;
    java.util.Random rand = new java.util.Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        numsList = new ArrayList<Integer>();
        locsMap = new HashMap<Integer, Integer>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        boolean contain = locsMap.containsKey(val);
        if (contain) return false;
        locsMap.put(val, numsList.size());
        numsList.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        boolean contain = locsMap.containsKey(val);
        if (!contain) return false;
        int loc = locsMap.get(val);
        if (loc < numsList.size() - 1) {
            int lastone = numsList.get(numsList.size() - 1);
            numsList.set(loc, lastone);
            locsMap.put(lastone, loc);
        }
        locsMap.remove(val);
        numsList.remove(numsList.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {

        return numsList.get(rand.nextInt(numsList.size()));
    }
}
