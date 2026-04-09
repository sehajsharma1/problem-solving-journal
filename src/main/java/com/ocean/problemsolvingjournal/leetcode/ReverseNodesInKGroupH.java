package com.ocean.problemsolvingjournal.leetcode;

/*
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
You may not alter the values in the list's nodes, only nodes themselves may be changed.

Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
*/
public class ReverseNodesInKGroupH {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        ListNode node = dummy.next;
        int length = count % k == 0 ? count : count - count % k;
        for (int i = 1; i <= length; i = i + k) {
            node = reverseBetween(node, i, i + (k - 1));
        }
        return node;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next;
        ListNode next = null;
        for (int i = 0; i < right - left; i++) {
            next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;

        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        ReverseNodesInKGroupH.ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();

    }

    public static void main(String[] args) {
        ReverseNodesInKGroupH obj = new ReverseNodesInKGroupH();

        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
        ReverseNodesInKGroupH.ListNode head = new ReverseNodesInKGroupH.ListNode(1);
        head.next = new ReverseNodesInKGroupH.ListNode(2);
        head.next.next = new ReverseNodesInKGroupH.ListNode(3);
        head.next.next.next = new ReverseNodesInKGroupH.ListNode(4);
        head.next.next.next.next = new ReverseNodesInKGroupH.ListNode(5);
        head.next.next.next.next.next = new ReverseNodesInKGroupH.ListNode(6);
        head.next.next.next.next.next.next = new ReverseNodesInKGroupH.ListNode(7);
        head.next.next.next.next.next.next.next = new ReverseNodesInKGroupH.ListNode(8);
        head.next.next.next.next.next.next.next.next = new ReverseNodesInKGroupH.ListNode(9);

        System.out.println("Original list:");
        printList(head);

        head = obj.reverseKGroup(head, 4);

        System.out.println("List after reversing between 4 and 8:");
        printList(head);
    }
}
