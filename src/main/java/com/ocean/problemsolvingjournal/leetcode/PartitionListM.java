package com.ocean.problemsolvingjournal.leetcode;

/*
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
*/
public class PartitionListM {

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

    public ListNode partition(ListNode head, int x) {

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode tempHead = head;

        while (head != null) {
            if (head.val < x) {
                prev.next = new ListNode(head.val);
                prev = prev.next;
            }
            head = head.next;
        }
        ListNode last = prev;
        while (tempHead != null) {
            if (tempHead.val >= x) {
                last.next = new ListNode(tempHead.val);
                last = last.next;
            }
            tempHead = tempHead.next;
        }
        return dummy.next;
    }

    public ListNode partitionV1(ListNode head, int x) {
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);

        ListNode small = smallDummy;
        ListNode large = largeDummy;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }

        large.next = null;

        small.next = largeDummy.next;

        return smallDummy.next;
    }

    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PartitionListM obj = new PartitionListM();

        // Create linked list: 1 -> 4 -> 2 -> 2 -> 5 -> 2
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        System.out.println("Original list:");
        printList(head);

        head = obj.partitionV1(head, 3);

        System.out.println("List after reversing between 4 and 8:");
        printList(head);
    }
}
