package com.ocean.problemsolvingjournal.leetcode;

/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys strictly less than the node's key.
The right subtree of a node contains only nodes with keys strictly greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [2,1,3]
Output: true
*/
public class ValidateBinarySearchTreeM {

    boolean isValid = true;
    long minValue = Long.MIN_VALUE;
    private long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {

        findValidBST(root);
        return isValid;
    }

    public void findValidBST(TreeNode root) {
        if (root == null || !isValid)
            return;
        findValidBST(root.left);
        if (root.val > minValue) {
            minValue = root.val;
        } else {
            isValid = false;
            return;
        }
        findValidBST(root.right);
    }

    public boolean isValidBSTV1(TreeNode root) {
        return inorder(root);
    }

    private boolean inorder(TreeNode node) {
        if (node == null) return true;

        if (!inorder(node.left)) {
            return false;
        }

        if (node.val <= prev) {
            return false;
        }
        prev = node.val;

        return inorder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        root.right.left = new TreeNode(3);
        ValidateBinarySearchTreeM obj = new ValidateBinarySearchTreeM();
        System.out.println(obj.isValidBSTV1(root));

    }
}
