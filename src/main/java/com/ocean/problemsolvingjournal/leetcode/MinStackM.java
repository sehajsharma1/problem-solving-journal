package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.Collections;
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]
*/
public class MinStackM {
    ArrayList<Integer> list;
    int min = Integer.MAX_VALUE;
    int top = 0;


    public MinStackM() {
        list = new ArrayList<>();
    }

    public void push(int val) {
        list.add(val);
        if (val < min) {
            min = val;
        }
        top = list.get(list.size() - 1);


    }

    public void pop() {
        list.remove(list.size() - 1);
        top = !list.isEmpty() ? list.get(list.size() - 1) : Integer.MAX_VALUE;
        min = !list.isEmpty() ? Collections.min(list) : Integer.MAX_VALUE;
    }

    public int top() {
        return top;
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {

        MinStackM stack = new MinStackM();

        // Operations based on your input list
        System.out.println("Operation: MinStackM()");

        stack.push(1);
        System.out.println("push(1)");

        stack.push(2);
        System.out.println("push(2)");

        stack.push(3);
        System.out.println("push(3)");

        System.out.println("top(): " + stack.top());

        stack.pop();
        System.out.println("pop()");

        System.out.println("getMin(): " + stack.getMin());

        stack.pop();
        System.out.println("pop()");

        System.out.println("getMin(): " + stack.getMin());

        stack.pop();
        System.out.println("pop()");

        stack.push(-1);
        System.out.println("push(-1)");

        System.out.println("top(): " + stack.top());

        System.out.println("getMin(): " + stack.getMin());

        stack.push(-2);
        System.out.println("push(-2)");

        System.out.println("top(): " + stack.top());

        System.out.println("getMin(): " + stack.getMin());

        stack.pop();
        System.out.println("pop()");

        System.out.println("getMin(): " + stack.getMin());
    }
}

