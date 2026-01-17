package com.ocean.problemsolvingjournal.leetcode;

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
class MinStack {

    private int[] stack;       // main stack
    private int[] minStack;    // stores minimums
    private int top;           // top pointer

    public MinStack() {
        stack = new int[100000];       // enough for constraints
        minStack = new int[100000];
        top = -1;
    }

    public void push(int val) {
        top++;

        stack[top] = val;

        if (top == 0) {
            minStack[top] = val;
        } else {
            minStack[top] = Math.min(val, minStack[top - 1]);
        }
    }

    public void pop() {
        top--;
    }

    public int top() {
        return stack[top];
    }

    public int getMin() {
        return minStack[top];
    }

    public static void main(String[] args) {

        MinStack stack = new MinStack();

        // Operations based on your input list
        System.out.println("Operation: MinStackM()");

        stack.push(11);
        System.out.println("push(11)");

        stack.push(10);
        System.out.println("push(10)");

        stack.push(9);
        System.out.println("push(9)");

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

