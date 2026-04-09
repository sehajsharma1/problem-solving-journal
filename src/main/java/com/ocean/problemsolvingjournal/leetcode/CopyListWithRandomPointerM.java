package com.ocean.problemsolvingjournal.leetcode;

import java.util.HashMap;

/*A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
*/
public class CopyListWithRandomPointerM {
    static class ListNode {
        int val;
        ListNode next;
        ListNode random;

        ListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public ListNode copyRandomList(ListNode head) {

        ListNode original = new ListNode(0);
        original.next = head;
        ListNode copy = new ListNode(0);
        ListNode dummy = copy;
        HashMap<ListNode, ListNode> map = new HashMap<>(1010);
        while (head != null) {
            dummy.next = new ListNode(head.val);
            dummy = dummy.next;
            map.put(head, dummy);
            head = head.next;
        }

        map.forEach((k, v) -> {
            ListNode random = k.random;
            v.random = map.get(random);
        });

        return copy.next;
    }

    public ListNode copyRandomListV1(ListNode head) {
        if (head == null) return null;

        // Step 1: Insert copied nodes in between original nodes
        ListNode curr = head;
        while (curr != null) {
            ListNode copy = new ListNode(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Assign random pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate the two lists
        curr = head;
        ListNode dummy = new ListNode(0);
        ListNode copyCurr = dummy;

        while (curr != null) {
            ListNode copy = curr.next;
            curr.next = copy.next;

            copyCurr.next = copy;
            copyCurr = copy;

            curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {

        // Create nodes
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);

        // Set next pointers: 1 -> 2 -> 3
        n1.next = n2;
        n2.next = n3;

        // Set random pointers
        n1.random = n3; // 1 -> 3
        n2.random = n1; // 2 -> 1
        n3.random = n3; // 3 -> itself

        // Call your method
        CopyListWithRandomPointerM obj = new CopyListWithRandomPointerM();
        ListNode copiedHead = obj.copyRandomList(n1);

        // Print original list
        System.out.println("Original List:");
        printList(n1);

        // Print copied list
        System.out.println("\nCopied List:");
        printList(copiedHead);
    }

    // Helper method to print list
    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            int randomVal = (temp.random != null) ? temp.random.val : -1;
            System.out.println("Val: " + temp.val + ", Random: " + randomVal);
            temp = temp.next;
        }
    }

}
