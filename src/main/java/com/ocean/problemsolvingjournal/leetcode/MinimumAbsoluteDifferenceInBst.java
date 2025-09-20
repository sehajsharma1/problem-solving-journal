package com.ocean.problemsolvingjournal.leetcode;

import java.util.Stack;

/*Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.*/
public class MinimumAbsoluteDifferenceInBst {

    Integer prev = null;  // Previous node in inorder traversal
    int minDiff = Integer.MAX_VALUE;

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Method to find minimum absolute difference in BST
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }
    // using stack
    public int getMinimumDifferenceV1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        Integer prev = null;
        int minDiff = Integer.MAX_VALUE;

        while (current != null || !stack.isEmpty()) {
            // Go as far left as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process the node
            current = stack.pop();
            if (prev != null) {
                minDiff = Math.min(minDiff, current.val - prev);
            }
            prev = current.val;

            // Move right
            current = current.right;
        }

        return minDiff;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        // Left subtree
        inorder(node.left);

        // Process current node
        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev);
        }
        prev = node.val;

        // Right subtree
        inorder(node.right);
    }


    public static void main(String[] args) {
        // Example BST:
        //      4
        //     / \
        //    2   6
        //   / \
        //  1   3
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        MinimumAbsoluteDifferenceInBst solver = new MinimumAbsoluteDifferenceInBst();
        int result = solver.getMinimumDifference(root);
        System.out.println("Minimum absolute difference in BST: " + result);
        System.out.println("Minimum absolute difference in BST using stack: " + solver.getMinimumDifferenceV1(root));
    }
}
