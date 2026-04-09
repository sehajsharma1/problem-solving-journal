package com.ocean.problemsolvingjournal.leetcode;


import java.util.LinkedList;
import java.util.Queue;

/*Given a binary tree
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
*/
public class PopulatingNextRightPointersEachNodeIIM {

    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;

        Node curr = root;

        while (curr != null) {

            Node nextStart = null;
            Node prev = null;

            while (curr != null) {

                if (curr.left != null) {
                    if (nextStart == null) {
                        nextStart = curr.left;
                    }
                    if (prev != null) {
                        prev.next = curr.left;
                    }
                    prev = curr.left;
                }

                if (curr.right != null) {
                    if (nextStart == null) {
                        nextStart = curr.right;
                    }
                    if (prev != null) {
                        prev.next = curr.right;
                    }
                    prev = curr.right;
                }

                curr = curr.next;
            }

            curr = nextStart;
        }

        return root;
    }

    public Node connectV1(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            Node prev = null;

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                if (prev != null) {
                    prev.next = curr;
                }

                prev = curr;

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            prev.next = null;
        }

        return root;
    }

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        PopulatingNextRightPointersEachNodeIIM obj = new PopulatingNextRightPointersEachNodeIIM();
        obj.connectV1(root);

        Node level = root;
        while (level != null) {
            Node curr = level;
            while (curr != null) {
                System.out.print(curr.val + " -> ");
                curr = curr.next;
            }
            System.out.println("null");

            if (level.left != null) level = level.left;
            else if (level.right != null) level = level.right;
            else level = level.next;
        }
    }
}
