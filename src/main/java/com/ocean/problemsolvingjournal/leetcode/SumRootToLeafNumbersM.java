package com.ocean.problemsolvingjournal.leetcode;
/*
You are given the root of a binary tree containing digits from 0 to 9 only.
Each root-to-leaf path in the tree represents a number.
For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
A leaf node is a node with no children.

Input: root = [1,2,3]
Output: 25

*/
public class SumRootToLeafNumbersM {
    // static TreeNode class
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // solution method
    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    // DFS (preorder-style)
    private static int dfs(TreeNode node, int current) {

        if (node == null) {
            return 0;
        }

        current = current * 10 + node.val;

        // if leaf node
        if (node.left == null && node.right == null) {
            return current;
        }

        return dfs(node.left, current) + dfs(node.right, current);

    }

    // main method
    public static void main(String[] args) {
    /*
            4
          /   \
         9     0
        / \     \
       5   1     7
    */
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(9);
        root.right = new TreeNode(0);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        root.right.right = new TreeNode(7);

        int result = sumNumbers(root);
        System.out.println(result); // Output: 1393
    }
}
