package com.ocean.problemsolvingjournal.leetcode;

/*
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
*/
public class ConstructBinaryTreeFromInorderAndPostorderTraversalM {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int[] inorderIdx = new int[]{postorder.length - 1};
        int[] postorderIdx = new int[]{postorder.length - 1};
        return buildTreeHelper(inorder, postorder, inorderIdx, postorderIdx, Integer.MIN_VALUE);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int[] inorderIdx, int[] postorderIdx, int stopValue) {
        // base cases
        if (inorderIdx[0] < 0 || inorder[inorderIdx[0]] == stopValue) return null;

        // build root
        int rootVal = postorder[postorderIdx[0]--];
        TreeNode root = new TreeNode(rootVal);

        // subproblems
        root.right = buildTreeHelper(inorder, postorder, inorderIdx, postorderIdx, rootVal);
        inorderIdx[0]--; // skip root
        root.left = buildTreeHelper(inorder, postorder, inorderIdx, postorderIdx, stopValue);

        return root;
    }

    // new: main to demonstrate buildTree
    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        ConstructBinaryTreeFromInorderAndPostorderTraversalM solver = new ConstructBinaryTreeFromInorderAndPostorderTraversalM();
        TreeNode root = solver.buildTree(inorder, postorder);

        System.out.print("binary tree: ");
        printPreorder(root);
        System.out.println();
    }

    private static void printPreorder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }
}
