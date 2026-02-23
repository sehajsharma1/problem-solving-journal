package com.ocean.problemsolvingjournal.leetcode;

/*A tree is symmetric if:

The left child of the left subtree equals the right child of the right subtree.

The right child of the left subtree equals the left child of the right subtree.

Both subtrees are recursively symmetric.*/

public class SymmetricTree {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        // Example symmetric tree: [1,2,2,3,4,4,3]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        SymmetricTree st = new SymmetricTree();
        boolean result = st.isSymmetric(root);
        System.out.println(result);
    }
}
