package com.ocean.problemsolvingjournal.leetcode;

/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
*/
public class LowestCommonAncestorOfABinaryTreeM {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // LCA method
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return (left != null) ? left : right;
    }

    public static void main(String[] args) {

        /*
                3
               / \
              5   1
             / \ / \
            6  2 0  8
              / \
             7   4
        */

        // Creating nodes
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Choose nodes p and q
        TreeNode p = root.left;              // 5
        TreeNode q = root.left.right.right;  // 4

        // Call LCA
        TreeNode lca = lowestCommonAncestor(root, p, q);

        // Print result
        System.out.println("LCA: " + lca.val);
    }
}