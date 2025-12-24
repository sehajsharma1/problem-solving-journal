package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
*/
public class FlattenBinaryTreeToLinkedList {
    List<Integer> list = new ArrayList<>();
    TreeNode prev = null;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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

    public void flatten(TreeNode root) {
        TreeNode dummy = root;
        preOrder(root);
        int i = 1;
        while (i < list.size()) {
            dummy.right = new TreeNode(list.get(i++));
            dummy.left = null;
            dummy = dummy.right;
        }

    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void flattenV1(TreeNode root) {
        if (root == null) return;

        // flatten right subtree first
        flattenV1(root.right);

        // flatten left subtree
        flattenV1(root.left);

        // rearrange pointers
        root.right = prev;
        root.left = null;

        // move prev pointer
        prev = root;
    }


    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList obj = new FlattenBinaryTreeToLinkedList();

        // Construct sample tree:
        //      1
        //     / \
        //    2   5
        //   / \   \
        //  3   4   6
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6))
        );

        obj.flattenV1 (root);

        // Print flattened list (should print pre-order: 1 2 3 4 5 6)
        TreeNode cur = root;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
    }

}
