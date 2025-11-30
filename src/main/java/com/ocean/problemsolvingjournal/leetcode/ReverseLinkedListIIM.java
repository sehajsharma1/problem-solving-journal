package com.ocean.problemsolvingjournal.leetcode;

/*Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
*/
public class ReverseLinkedListIIM {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // Function to reverse nodes between left and right
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 1️⃣ Move `prev` to the node before 'left'
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // 2️⃣ Reverse the sublist between left and right
        ListNode curr = prev.next;      // start of the segment
        ListNode next = null;

        for (int i = 0; i < right - left; i++) {
            next = curr.next;           // pick node after curr
            curr.next = next.next;      // remove `next` from list
            next.next = prev.next;      // insert `next` after `prev`
            prev.next = next;           // move `next` to front of sublist
        }

        return dummy.next;
    }

    // Helper method to print list
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // Main method to test
    public static void main(String[] args) {
        ReverseLinkedListIIM obj = new ReverseLinkedListIIM();

        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);

        System.out.println("Original list:");
        printList(head);

        // Reverse between positions 2 and 8
        head = obj.reverseBetween(head, 2, 8);

        System.out.println("List after reversing between 2 and 8:");
        printList(head);
    }

}
