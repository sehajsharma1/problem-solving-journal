package com.ocean.problemsolvingjournal.leetcode;

/*Given the head of a linked list, rotate the list to the right by k places.

Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
 */

public class RotateListM {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = new ListNode(0);
        ListNode leftDummy = head;
        ListNode left = head;
        int nodeSize = 0;
        while (head != null) {
            nodeSize++;
            head = head.next;
        }
        int rotate = k;
        if (k >= nodeSize) {
            rotate = k % nodeSize;
        }
        for (int i = 1; i <= nodeSize; i++) {
            if (nodeSize - rotate == i) {
                node.next = leftDummy.next;
                leftDummy.next = null;
                break;
            }
            leftDummy = leftDummy.next;
        }
        ListNode dummy = node;
        while (true) {
            if (node.next == null) {
                node.next = left;
                break;
            }
            node = node.next;

        }
        return dummy.next;   // as you said, don't implement
    }

    public ListNode rotateRightV1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Find length and get tail
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Make it circular
        tail.next = head;

        // Step 3: Normalize k
        k = k % length;
        int stepsToNewHead = length - k;

        // Step 4: Move to new head
        ListNode newTail = tail;
        while (stepsToNewHead-- > 0) {
            newTail = newTail.next;
        }

        // Step 5: Break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {

        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));

        RotateListM sol = new RotateListM();

        int k = 2;

        ListNode result = sol.rotateRightV1(head, k);

        // Print returned list
        printList(result);
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
}
