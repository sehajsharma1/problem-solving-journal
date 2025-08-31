package leetcode.code.java;

/*Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*/


public class MaximumDepthOfBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {
        }

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
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        int depth = new MaximumDepthOfBinaryTree().maxDepth(root);
        System.out.println(depth);
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int left = maxDepth(root.left);     // Recursive call on left subtree
        int right = maxDepth(root.right);   // Recursive call on right subtree

        return Math.max(left, right) + 1;   // Add 1 for current node
    }
}
