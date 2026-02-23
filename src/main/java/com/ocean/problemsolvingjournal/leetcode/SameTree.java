package com.ocean.problemsolvingjournal.leetcode;

/*Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Input: p = [1,2,3], q = [1,2,3]
Output: true
*/
public class SameTree {

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
        TreeNode p = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(2));
        boolean result = new SameTree().isSameTree(p, q);
        System.out.println(result);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // If both nodes are null, trees are the same at this branch
        if (p == null && q == null) {
            return true;
        }

        // If only one of them is null or values are different, not same
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
