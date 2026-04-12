package com.ocean.problemsolvingjournal.leetcode;

import java.util.*;

/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
*/
public class BinaryTreeZigzagLevelOrderTraversalM {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        listReverse(result);
        return result;
    }

    private void dfs(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) return;

        if (result.size() == level) {
            result.add(new ArrayList<>());
        }

        result.get(level).add(node.val);

        dfs(node.left, level + 1, result);
        dfs(node.right, level + 1, result);
    }

    private void listReverse(List<List<Integer>> list) {
        for (int i = 1; i < list.size(); i++) {
            if (i % 2 != 0) {
                Collections.reverse(list.get(i));
            }
        }
    }

    public List<List<Integer>> zigzagLevelOrderV1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> levelData = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                if (leftToRight) {
                    levelData.add(currentNode.val);
                } else {
                    levelData.addFirst(currentNode.val);
                }
                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }
            result.add(levelData);
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTreeZigzagLevelOrderTraversalM obj = new BinaryTreeZigzagLevelOrderTraversalM();
        System.out.println(obj.zigzagLevelOrderV1(root));

    }
}
