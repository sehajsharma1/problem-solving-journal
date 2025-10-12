package com.ocean.problemsolvingjournal.leetcode;

/*Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted.
 */

public class ConvertSortedArrayToBinarySearchTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {

        return build(nums, 0, nums.length - 1);

    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2; // avoid overflow
        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);

        return root;
    }

    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree converter = new ConvertSortedArrayToBinarySearchTree();
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = converter.sortedArrayToBST(nums);
    }


}
