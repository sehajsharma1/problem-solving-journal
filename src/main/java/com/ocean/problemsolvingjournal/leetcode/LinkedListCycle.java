package leetcode.code.java;


/*Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.



Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).*/

public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode head = new ListNode(3), node2 = new ListNode(2), node0 = new ListNode(0), nodeMinus4 = new ListNode(-4);
        head.next = node2;
        node2.next = node0;
        node0.next = nodeMinus4;
        nodeMinus4.next = node2;
        System.out.println("Has cycle: " + new LinkedListCycle().hasCycle(head));
    }

    /*Approach: Floyd’s Cycle Detection
Use two pointers:

slow moves one step at a time.

fast moves two steps at a time.

If there is a cycle, the two pointers will eventually meet.

If fast reaches the end (null), there is no cycle.*/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
