package leetcode.code.java;

/*Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.
*/
public class PathSum {
    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        // If leaf node, check if targetSum equals node value
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Recurse on left and right subtrees with reduced target
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        // Example tree: [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        PathSum ps = new PathSum();
        boolean result = ps.hasPathSum(root, 22);
        System.out.println(result);
    }
}
