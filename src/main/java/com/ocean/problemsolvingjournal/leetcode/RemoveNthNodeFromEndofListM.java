package com.ocean.problemsolvingjournal.leetcode;

/*Given the head of a linked list, remove the nth node from the end of the list and return its head.
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
 */
public class RemoveNthNodeFromEndofListM {
    // Static ListNode class
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 1;
        ListNode node = new ListNode(0);
        node.next = head;
        while (head != null) {
            count++;
            head = head.next;
        }
        ListNode prev = null;
        ListNode next = null;
        ListNode curr = node;
        while (count >= n) {
            if (count == n) {
                curr = prev;
                curr.next = next;
                break;
            }
            prev = curr;
            next = curr.next.next;
            curr = curr.next;
            --count;
        }
        return node.next;
    }

    public ListNode removeNthFromEndV1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast n+1 steps so slow stops before the node to delete
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both fast and slow
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the node
        slow.next = slow.next.next;

        return dummy.next;
    }

    // Helper to print list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    // Main method to test
    public static void main(String[] args) {
        RemoveNthNodeFromEndofListM obj = new RemoveNthNodeFromEndofListM();
        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Original list:");
        printList(head);
        // Call your method
        head = obj.removeNthFromEndV1(head, 2);  // Calling method
        System.out.println("List after removing 2nd node from end:");
        printList(head);
    }
}
