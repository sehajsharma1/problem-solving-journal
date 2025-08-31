package leetcode.code.java;

/*Given the root of a complete binary tree, return the number of the nodes in the tree.*/
public class CountCompleteTreeNodes {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (leftHeight == rightHeight) {
            // Left subtree is perfect
            return (1 << leftHeight) + countNodes(root.right);
        } else {
            // Right subtree is perfect
            return (1 << rightHeight) + countNodes(root.left);
        }
    }

    // Computes height of a tree by going only left
    private int getHeight(TreeNode node) {
        int h = 0;
        while (node != null) {
            h++;
            node = node.left;
        }
        return h;
    }

    // Example usage
    public static void main(String[] args) {
        // Build a complete binary tree: [1,2,3,4,5,6]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        CountCompleteTreeNodes solution = new CountCompleteTreeNodes();
        System.out.println(solution.countNodes(root)); // Output: 6
    }
}
