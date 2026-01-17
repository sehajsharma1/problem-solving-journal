package com.ocean.problemsolvingjournal.leetcode;
/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
*/

class BinaryTreeFromPreorderAndInorderTraversalM {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int i = 0; // inorder index
    private int p = 0; // preorder index

    // ---------- Build Tree ----------
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (p >= preorder.length) {
            return null;
        }

        if (inorder[i] == stop) {
            i++;
            return null;
        }

        TreeNode node = new TreeNode(preorder[p++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);

        return node;
    }

    // ---------- Print Inorder (to verify) ----------
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        BinaryTreeFromPreorderAndInorderTraversalM obj =
                new BinaryTreeFromPreorderAndInorderTraversalM();

        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder  = {4, 2, 5, 1, 6, 3, 7};

        TreeNode root = obj.buildTree(preorder, inorder);

        System.out.print("Inorder traversal of constructed tree: ");
        printInorder(root);
    }
}
