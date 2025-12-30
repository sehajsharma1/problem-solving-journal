package com.ocean.problemsolvingjournal.leetcode;
/*
Given the head of a linked list, return the list after sorting it in ascending order.
Input: head = [4,2,1,3]
Output: [1,2,3,4]
*/
class SortListM {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: split list into two halves
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // cut the list

        // Step 2: sort both halves
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // Step 3: merge sorted halves
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
    public static void main(String[] args) {
        int[] values = {-1,5,3,4,0,2,8,-2}; // sample input; modify as needed
        ListNode head = buildList(values);

        System.out.println("Original list: " + (head != null ? head.toString() : "null"));

        SortListM sorter = new SortListM();
        ListNode sorted = sorter.sortListV1(head);

        System.out.println("Sorted list:   " + (sorted != null ? sorted.toString() : "null"));
    }

    private static ListNode buildList(int[] vals) {
        if (vals == null || vals.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode sortListV1(ListNode head) {
        if (head == null || head.next == null) return head;

        int length = getLength(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (int size = 1; size < length; size <<= 1) {
            ListNode curr = dummy.next;
            ListNode tail = dummy;

            while (curr != null) {
                ListNode left = curr;
                ListNode right = split(left, size);
                curr = split(right, size);

                tail.next = merge(left, right);
                while (tail.next != null) tail = tail.next;
            }
        }
        return dummy.next;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    // cut list after n nodes and return next head
    private ListNode split(ListNode head, int n) {
        while (--n > 0 && head != null) {
            head = head.next;
        }
        if (head == null) return null;

        ListNode next = head.next;
        head.next = null;
        return next;
    }
}

