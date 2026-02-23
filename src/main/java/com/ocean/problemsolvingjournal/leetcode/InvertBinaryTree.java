package com.ocean.problemsolvingjournal.leetcode;

/*Given the root of a binary tree, invert the tree, and return its root.
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
*/
public class InvertBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode node2 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode node7 = new TreeNode(7, new TreeNode(6), new TreeNode(9));
        TreeNode root = new TreeNode(4, node2, node7);
        new InvertBinaryTree().invertTree(root);
    }

    private TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        final TreeNode left = root.left,
                right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

}
